package org.fitnesstracker.controller;

import org.fitnesstracker.model.User;
import org.fitnesstracker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest
{

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks( this );
        user = new User();
        user.setId( 1L );

        user.setPassword( "password123" );
    }

    @Test
    void testCreateUser()
    {
        when( userService.saveUser( user ) ).thenReturn( user );

        ResponseEntity<String> result = userController.createUser( user );
        assertNotNull( result );

    }

    @Test
    void testGetUserById()
    {
        when( userService.getUserById( 1L ) ).thenReturn( Optional.of( user ) );

        Optional<User> result = Optional.ofNullable( userController.getUserById( 1L ).getBody() );
        assertTrue( result.isPresent() );
        assertEquals( 1L, result.get().getId() );

        assertEquals( "password123", result.get().getPassword() );

        verify( userService, times( 1 ) ).getUserById( 1L );
    }

    @Test
    void testGetUserById_NotFound()
    {
        when( userService.getUserById( 1L ) ).thenReturn( Optional.empty() );

        ResponseEntity<User> result = userController.getUserById( 1L );

        verify( userService, times( 1 ) ).getUserById( 1L );
    }

    @Test
    void testDeleteUser()
    {
        doNothing().when( userService ).deleteUser( 1L );

        userController.deleteUser( 1L );
        verify( userService, times( 1 ) ).deleteUser( 1L );
    }

    @Test
    void testgetUsers()
    {
        when( userService.getUsers() ).thenReturn( users() );
        ResponseEntity<List<User>> user = userController.getUsers();
        assertNotNull( user );
        assertEquals( HttpStatus.OK, user.getStatusCode() );
    }

    private static List<User> users()
    {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId( 1L );
        user1.setPassword( "234" );
        List<String> strings = new ArrayList<>();
        strings.add( "a" );
        user1.setRoles( strings );
        users.add( user1 );
        return users;
    }
}
