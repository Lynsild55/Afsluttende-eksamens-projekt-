package fr.hovedopgave.demo.service.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import fr.hovedopgave.demo.dto.RegisterDTO;
import fr.hovedopgave.demo.model.UserEntity;
import fr.hovedopgave.demo.model.Role;
import fr.hovedopgave.demo.repository.RoleRepository;
import fr.hovedopgave.demo.repository.UserRepository;
import fr.hovedopgave.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // Constructor for dependency injection
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegisterDTO registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());
        
        Optional<Role> roleOptional = roleRepository.findByName("USER");

        if (roleOptional.isPresent()) {
            Role role = roleOptional.get(); // Get the role from Optional
            user.setRoles(Arrays.asList(role)); 
        } else {
            // Handle the case where the role is not found
            System.err.println("Role 'USER' not found in the database");
        }

        // Save the user
        userRepository.save(user);
    }
}