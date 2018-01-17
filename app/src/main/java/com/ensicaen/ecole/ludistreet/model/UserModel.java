package com.ensicaen.ecole.ludistreet.model;

import java.util.List;

/**
 * Created by Thibaud on 16/01/2018.
 */

/**
 * Model use to defined the user
 */
public class UserModel {
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String username;

    public UserModel(String email, String firstname, String lastname, String password, String username) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
