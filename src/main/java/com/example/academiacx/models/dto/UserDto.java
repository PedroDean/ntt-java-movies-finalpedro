package com.example.academiacx.models.dto;

import com.example.academiacx.models.UserModel;

public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String email;

    public UserDto() {
    }

    public UserDto(UserModel userModel) {

        this.id = userModel.getId();
        this.name = userModel.getName();
        this.email = userModel.getEmail();
        this.username = userModel.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
