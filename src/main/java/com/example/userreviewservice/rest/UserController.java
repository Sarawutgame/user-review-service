package com.example.userreviewservice.rest;

import com.example.userreviewservice.command.CreateUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CommandGateway commandGateway;

    @Autowired
    public UserController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createUser(@RequestBody CreateUserModel model){
        CreateUserCommand command = CreateUserCommand.builder()
                ._id(UUID.randomUUID().toString().toString())
                .username(model.getUsername())
                .name(model.getName())
                .email(model.getEmail())
                .birthday(model.getBirthday())
                .phone(model.getPhone())
                .imageId(model.getImageId())
                .role(model.getRole()).build();
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
