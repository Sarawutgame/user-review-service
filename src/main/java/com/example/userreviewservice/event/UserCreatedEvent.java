package com.example.userreviewservice.event;

import lombok.Data;

@Data
public class UserCreatedEvent {

    private String _id;
    private String username;
    private String name;
    private String email;
    private String birthday;
    private String phone;
    private String imageId;
    private String role;
}
