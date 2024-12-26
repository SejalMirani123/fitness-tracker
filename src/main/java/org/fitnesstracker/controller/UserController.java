package org.fitnesstracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.fitnesstracker.model.User;
import org.fitnesstracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/users" )
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @Operation( summary = "Create a new user", description = "Adds a new user to the Fitness Tracker system.")
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {

        return ResponseEntity.ok("User created!");
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers()
    {
        return ResponseEntity.ok( userService.getUsers() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<User> getUserById( @PathVariable Long id )
    {
        return userService.getUserById( id )
                          .map( ResponseEntity::ok )
                          .orElse( ResponseEntity.notFound().build() );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Void> deleteUser( @PathVariable Long id )
    {
        userService.deleteUser( id );
        return ResponseEntity.noContent().build();
    }
}