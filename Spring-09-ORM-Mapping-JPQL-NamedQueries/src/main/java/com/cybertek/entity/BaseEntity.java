package com.cybertek.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
    @javax.persistence.Id
    private Long id;
}
