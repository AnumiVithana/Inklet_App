package com.example.inklet.service;

import com.example.inklet.dto.UserDTO;
import com.example.inklet.entity.User;
import com.example.inklet.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserDTO> getAllUsersExcept(String email) {
        return userRepository.findAll().stream()
                .filter(user -> !user.getEmail().equals(email))
                .map(u -> new UserDTO(u.getFirstName(), u.getLastName(), u.getEmail()))
                .collect(Collectors.toList());
    }
}