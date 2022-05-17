package com.example.bukmacher.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        User userToAdd = new User();
        userToAdd.setUsername(user.getUsername());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        userToAdd.setPassword(encryptedPassword);
        List<UserRole> list = Collections.singletonList(new UserRole(userToAdd, Role.ROLE_USER));
        userToAdd.setRoles(new HashSet<>(list));
        userToAdd.setName(user.getName());
        userToAdd.setLastName(user.getLastName());
        userToAdd.setEmail(user.getEmail());

        userRepository.save(userToAdd);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Set<UserRole> getUserRoles(Long id) {
        return userRepository.findById(id).get().getRoles();
    }

    public User findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return user;
    }

    public void deleteAdminRoleByUserId(Long id) {
        userRepository.findById(id).ifPresent(user -> {
                    user.getRoles().removeIf(userRole -> userRole.getRole().equals(Role.ROLE_ADMIN));
                    userRepository.save(user);
                }
        );
    }

    public void addAdminRoleByUserId(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            user.getRoles().add(new UserRole(user, Role.ROLE_ADMIN));
            userRepository.save(user);
        });
    }

    public Optional<User> findUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    public void updateUserByUsername(String username, User user1) {
        userRepository.findByUsername(username).ifPresent(
                user -> {
                    user.setName(user1.getName());
                    user.setLastName(user1.getLastName());
                    user.setPassword(passwordEncoder.encode(user1.getPassword()));
                    userRepository.save(user);
                });
    }
}
