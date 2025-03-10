package eci.arep.property.service;

import eci.arep.property.dto.UserDto;
import eci.arep.property.model.UserEntity;
import eci.arep.property.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Iterable<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public boolean auth(UserDto userDto){
        UserEntity user = userRepository.findByEmail(userDto.getEmail());
        return BCrypt.checkpw(userDto.getPassword(), user.getPassword());
    }

    public UserEntity registerUser(UserDto userDto){
        UserEntity newUser = new UserEntity(userDto);
        return userRepository.save(newUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
