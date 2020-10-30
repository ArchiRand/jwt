package com.archirand.jwt.model;

import com.archirand.jwt.model.enums.Status;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -226728396933771540L;

    private Long id;

    private Date created = new Date();

    private Date updated;

    private Status status;
}
