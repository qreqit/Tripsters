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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    private String uploadDir = "src/main/resources/images";

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

    @Override
    public UserResponseDto getCurrentUser(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User nor "
                        + "foud with email: " + authentication.getName()));
        return userMapper.toDto(user);
    }

    @Override
    public ResponseEntity<String> uploadImage(MultipartFile file) {
        User user = getAuthenticatedUser();
        try {
            String filePath = saveImage(file);
            return ResponseEntity.ok("Image uploaded successfully: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading image");
        }
    }

    private String saveImage(MultipartFile file) throws IOException {
        User user = getAuthenticatedUser();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = "FileForUserWithId" + user.getId() + ".jpg";
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        user.setPhotoUrl(fileName);
        userRepository.save(user);
        return filePath.toString();
    }

    @Override
    public ResponseEntity<Resource> getImage(String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String authenticatedUserEmail = authentication.getName();

        return userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new EntityNotFoundException("Authenticated "
                        + "user not found"));
    }
}
