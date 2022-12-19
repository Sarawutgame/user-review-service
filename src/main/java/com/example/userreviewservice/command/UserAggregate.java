package com.example.userreviewservice.command;

import com.example.userreviewservice.event.UserCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String _id;
    private String username;
    private String name;
    private String email;
    private String birthday;
    private String phone;
    private String imageId;
    private String role;

    public UserAggregate() {
    }

    @CommandHandler
    public UserAggregate(CreateUserCommand createUserCommand){
        if(createUserCommand.getUsername() == null || createUserCommand.getUsername().isBlank()){
            throw new IllegalArgumentException("Name Can't Empty");
        }
        if(createUserCommand.getName() == null || createUserCommand.getName().isBlank()){
            throw new IllegalArgumentException("Name Can't Empty");
        }
        if(createUserCommand.getEmail() == null || createUserCommand.getEmail().isBlank()){
            throw new IllegalArgumentException("Name Can't Empty");
        }
        if(createUserCommand.getPhone() == null || createUserCommand.getPhone().isBlank()){
            throw new IllegalArgumentException("Name Can't Empty");
        }
        if(createUserCommand.getBirthday() == null || createUserCommand.getBirthday().isBlank()){
            throw new IllegalArgumentException("Name Can't Empty");
        }
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent();
        BeanUtils.copyProperties(createUserCommand, userCreatedEvent);
        AggregateLifecycle.apply(userCreatedEvent);
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent userCreatedEvent){
        this._id = userCreatedEvent.get_id();
        this.username = userCreatedEvent.getUsername();
        this.name = userCreatedEvent.getName();
        this.email = userCreatedEvent.getEmail();
        this.birthday = userCreatedEvent.getBirthday();
        this.phone = userCreatedEvent.getPhone();
        this.imageId = userCreatedEvent.getImageId();
        this.role = userCreatedEvent.getRole();

    }
}
