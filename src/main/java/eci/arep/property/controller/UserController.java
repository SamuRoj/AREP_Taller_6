package eci.arep.property.controller;

import eci.arep.property.dto.UserDto;
import eci.arep.property.model.UserEntity;
import eci.arep.property.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "https://srapacheserver.duckdns.org", allowedHeaders = "*", allowCredentials = "true")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Iterable<UserEntity>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/auth")
    public ResponseEntity<Void> authUser(@RequestBody UserDto userDto, HttpServletRequest request) {
        if(userService.auth(userDto)){
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER"));

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDto.getEmail(),
                    null,
                    authorities
            );

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            request.getSession().setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext
            );

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(401).build();
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        UserEntity newUser = userService.registerUser(userDto);
        URI uri = URI.create("/user/" + newUser.getId());
        return ResponseEntity.created(uri).body("/users/" + newUser.getId());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
