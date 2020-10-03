package com.archirand.jwt.model;

import com.archirand.jwt.model.enums.Status;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    private Long id;

    private Date created = new Date();

    private Date updated;

    private Status status;
}
