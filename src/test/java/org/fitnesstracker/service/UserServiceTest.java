package org.fitnesstracker.service;

import org.fitnesstracker.model.User;
import org.fitnesstracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest
{

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks( this );
        user = new User();
        user.setId( 1L );

    }

    @Test
     void testSaveUser()
    {
        when( userRepository.save( user ) ).thenReturn( user );
        User savedUser = userService.saveUser( user );
        assertNotNull( savedUser );
        verify( userRepository, times( 1 ) ).save( user );
    }

    @Test
    void testGetUsers()
    {
        List<User> users = List.of( user );
        when( userRepository.findAll() ).thenReturn( users );
        List<User> result = userService.getUsers();
        assertNotNull( result );
        assertEquals( 1, result.size() );
        verify( userRepository, times( 1 ) ).findAll();
    }

    @Test
     void testGetUserById()
    {
        when( userRepository.findById( 1L ) ).thenReturn( Optional.of( user ) );
        Optional<User> result = userService.getUserById( 1L );
        assertTrue( result.isPresent() );
        verify( userRepository, times( 1 ) ).findById( 1L );
    }

    @Test
    void testDeleteUser()
    {
        doNothing().when( userRepository ).deleteById( 1L );
        userService.deleteUser( 1L );
        verify( userRepository, times( 1 ) ).deleteById( 1L );
    }

    @Test
    void testGetUserById_UserNotFound()
    {
        when( userRepository.findById( 1L ) ).thenReturn( Optional.empty() );
        Optional<User> result = userService.getUserById( 1L );
        assertFalse( result.isPresent() );
        verify( userRepository, times( 1 ) ).findById( 1L );
    }
}
