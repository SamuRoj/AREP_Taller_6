package eci.arep.property.service;

import eci.arep.property.dto.UserDto;
import eci.arep.property.model.UserEntity;
import eci.arep.property.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    private UserEntity mockUser;

    @BeforeEach
    void setup(){
        mockUser = new UserEntity("email@email.com", "secretPassword");
    }

    @Test
    void UserService_auth(){
        when(userRepository.findByEmail(Mockito.any(String.class))).thenReturn(mockUser);

        UserDto userDto = new UserDto("email@email.com", "secretPassword");

        Assertions.assertTrue(userService.auth(userDto));
    }

    @Test
    void UserService_auth_failed(){
        when(userRepository.findByEmail(Mockito.any(String.class))).thenReturn(mockUser);

        UserDto userDto = new UserDto("email@email.com", "password");

        Assertions.assertFalse(userService.auth(userDto));
    }

    @Test
    void UserService_registerUser() {
        UserDto userDto = new UserDto("email@email.com", "secretPassword");

        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(mockUser);

        UserEntity userSaved = userService.registerUser(userDto);

        Assertions.assertNotNull(userSaved);
        Assertions.assertEquals("email@email.com", userSaved.getEmail());
        Assertions.assertTrue(BCrypt.checkpw("secretPassword", userSaved.getPassword()));
    }

}
