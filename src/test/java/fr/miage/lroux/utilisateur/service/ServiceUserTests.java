package fr.miage.lroux.utilisateur.service;

import fr.miage.lroux.utilisateur.entity.User;
import fr.miage.lroux.utilisateur.repository.RepoUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the ServiceUser class.
 */
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class ServiceUserTests {

    @Mock
    private RepoUser repoUser;

    @InjectMocks
    private ServiceUser serviceUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUser_ShouldCreateUser_WhenUserDoesNotExist() throws Exception {
        // Arrange
        User user = new User();
        user.setUserId(1L);
        when(repoUser.findById(user.getUserId())).thenReturn(Optional.empty());
        when(repoUser.save(user)).thenReturn(user);

        // Act
        User createdUser = serviceUser.createUser(user);

        // Assert
        assertNotNull(createdUser);
        assertEquals(user.getUserId(), createdUser.getUserId());
        verify(repoUser, times(1)).findById(user.getUserId());
        verify(repoUser, times(1)).save(user);
    }

    @Test
    public void createUser_ShouldThrowException_WhenUserAlreadyExists() {
        // Arrange
        User user = new User();
        user.setUserId(1L);
        when(repoUser.findById(user.getUserId())).thenReturn(Optional.of(user));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> serviceUser.createUser(user));
        assertEquals("A user already with this ID" + user.getUserId(), exception.getMessage());
        verify(repoUser, times(1)).findById(user.getUserId());
        verify(repoUser, never()).save(user);
    }

    @Test
    public void getUserById_ShouldReturnUser_WhenUserExists() throws Exception {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);
        when(repoUser.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User retrievedUser = serviceUser.getUserById(userId);

        // Assert
        assertNotNull(retrievedUser);
        assertEquals(userId, retrievedUser.getUserId());
        verify(repoUser, times(1)).findById(userId);
    }

    @Test
    public void getUserById_ShouldThrowException_WhenUserDoesNotExist() {
        // Arrange
        Long userId = 1L;
        when(repoUser.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> serviceUser.getUserById(userId));
        assertEquals("No user has been retrieved with this id " + userId, exception.getMessage());
        verify(repoUser, times(1)).findById(userId);
    }

    @Test
    public void deleteUserById_ShouldDeleteUser_WhenUserExists() throws Exception {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);
        when(repoUser.findById(userId)).thenReturn(Optional.of(user));

        // Act
        serviceUser.deleteUserById(userId);

        // Assert
        verify(repoUser, times(1)).findById(userId);
        verify(repoUser, times(1)).deleteById(userId);
    }

    @Test
    public void deleteUserById_ShouldThrowException_WhenUserDoesNotExist() {
        // Arrange
        Long userId = 1L;
        when(repoUser.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> serviceUser.deleteUserById(userId));
        assertEquals("No user has been retrieved with this id " + userId, exception.getMessage());
        verify(repoUser, times(1)).findById(userId);
        verify(repoUser, never()).deleteById(userId);
    }
}
