package com.example.tripsters.service.impl;

import com.example.tripsters.dto.user.UserRegistrationRequestDto;
import com.example.tripsters.dto.user.UserResponseDto;
import com.example.tripsters.exception.EntityNotFoundException;
import com.example.tripsters.exception.RegistrationException;
import com.example.tripsters.mapper.UserMapper;
import com.example.tripsters.model.Role;
import com.example.tripsters.model.User;
import com.example.tripsters.repository.RoleRepository;
import com.example.tripsters.repository.UserRepository;
import com.example.tripsters.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException("Can't register user with email: "
                    + requestDto.getEmail());
        }

        User user = userMapper.toModel(requestDto);
        Set<Role> roles = new HashSet<>();
        Role adminRole = roleRepository.findByName(Role.RoleName.ROLE_ADMIN)
                        .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        Role userRole = roleRepository.findByName(Role.RoleName.ROLE_USER)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        roles.add(userRole);
        roles.add(adminRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(userMapper::toDto)
                    .toList();
    }
}
