package arep.crudsystem.controller;

import arep.crudsystem.model.User;
import arep.crudsystem.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUserSuccess() {
        // Arrange
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        when(userService.registerUser(anyString(), anyString())).thenReturn(user);

        // Act
        ResponseEntity<String> response = authController.register("testuser", "password");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered: testuser", response.getBody());
        verify(userService, times(1)).registerUser("testuser", "password");
    }

    @Test
    void testRegisterUserFailure() {
        // Arrange
        when(userService.registerUser(anyString(), anyString())).thenThrow(new RuntimeException("Username already exists"));

        // Act
        ResponseEntity<String> response = authController.register("testuser", "password");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error registering user: Username already exists", response.getBody());
        verify(userService, times(1)).registerUser("testuser", "password");
    }

    @Test
    void testLoginSuccess() {
        // Arrange
        when(userService.authenticate(anyString(), anyString())).thenReturn(true);

        // Act
        ResponseEntity<Map<String, String>> response = authController.login("testuser", "password");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login successful", response.getBody().get("message"));
        assertEquals("https://taller6arep2025apache.duckdns.org/home.html", response.getBody().get("redirectUrl"));
        verify(userService, times(1)).authenticate("testuser", "password");
    }

    @Test
    void testLoginFailureInvalidCredentials() {
        // Arrange
        when(userService.authenticate(anyString(), anyString())).thenReturn(false);

        // Act
        ResponseEntity<Map<String, String>> response = authController.login("testuser", "password");

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid credentials", response.getBody().get("message"));
        verify(userService, times(1)).authenticate("testuser", "password");
    }

    @Test
    void testLoginFailureException() {
        // Arrange
        when(userService.authenticate(anyString(), anyString())).thenThrow(new RuntimeException("User not found"));

        // Act
        ResponseEntity<Map<String, String>> response = authController.login("testuser", "password");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error during login: User not found", response.getBody().get("message"));
        verify(userService, times(1)).authenticate("testuser", "password");
    }
}