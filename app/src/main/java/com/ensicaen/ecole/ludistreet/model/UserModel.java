package com.ensicaen.ecole.ludistreet.model;

import java.util.List;

/**
 * Created by Thibaud on 16/01/2018.
 */

/**
 * Model use to defined the user
 */
public class UserModel {
    private String name;
    private String firstName;
    private String email;
    private int score;
    private List<String> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
