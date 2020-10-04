package com.archirand.jwt.security.jwt;

import com.archirand.jwt.model.Role;
import com.archirand.jwt.security.jwt.exception.JwtAuthenticationException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Autowired
    private UserDetailsService userDetailsService;
    // Секретное слово, на основании которого мы будем шифровать и расшифровывать токен
    @Value("${jwt.token.secret")
    private String secret;
    // Количество миллисикунд, которое должно пройти до того, как токен истечет
    @Value("${jwt.token.expired")
    private Long expiredInMilliseconds;

    @PostConstruct
    private void postConstruct() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String token(String login, List<Role> roles) {
        // Разобраться что это такое и для чего оно
        Claims claims = Jwts.claims().setSubject(login);
        claims.put("roles", roles);

        Date now = new Date();
        Date expireIn = new Date(now.getTime() + expiredInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireIn)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication authentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(login(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String login(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return claims.getBody().getExpiration().before(new Date());
        } catch (JwtException exception) {
            throw new JwtAuthenticationException("JWT token is expired or invalid", exception);
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7);
        }
        return "";
    }

    private List<String> roleNames(List<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }
}
