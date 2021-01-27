package com.cybertek.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_account")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"}, ignoreUnknown = true) //add hibernateLazy.. whenever FetchType=LAZY (in the User field *see Ticket class)
public class User extends BaseEntity {

    private String email;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY) //whenever we retrieve=get we hide this field, we only show it when set
    private String password;

    private String username;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "account_details_id")
    @JsonManagedReference //should not show Account information
    private Account account;

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}