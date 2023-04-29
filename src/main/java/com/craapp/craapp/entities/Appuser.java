package com.craapp.craapp.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name =  "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appuser {



    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @Column(unique = true)
    private String email;
    private boolean emailVerified;
    private String roles;

    public Appuser(  String username, String password, String firstName, String lastName, String phoneNumber, String email, boolean emailVerified, String roles) {

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.emailVerified = emailVerified;
        this.roles = roles;
    }
}
