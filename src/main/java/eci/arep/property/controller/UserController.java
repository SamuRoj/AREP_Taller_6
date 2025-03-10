package eci.arep.property.controller;

import eci.arep.property.dto.UserDto;
import eci.arep.property.model.UserEntity;
import eci.arep.property.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth")
    public ResponseEntity<Void> authUser(@RequestBody UserDto userDto) {
        if(userService.auth(userDto)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        UserEntity newUser = userService.registerUser(userDto);
        URI uri = URI.create("/user/" + newUser.getId());
        return ResponseEntity.created(uri).body("/users/" + newUser.getId());
    }
}
