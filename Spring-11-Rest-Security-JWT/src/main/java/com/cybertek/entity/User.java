package com.cybertek.entity;

import com.cybertek.enums.UserRole;
import com.cybertek.enums.UserState;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name="users")
@Getter
@Setter
@Where(clause="is_deleted=false")
public class User extends BaseEntity {
    @Column(nullable=false,unique=true)
    private String email;

    @Column(nullable=false,unique=true)
    private String username;

    @Column(nullable=false)
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    @Enumerated(EnumType.STRING)
    private UserState state;

    private Boolean isVerified;


}
