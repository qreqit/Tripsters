package com.example.tripsters.controller;

import com.example.tripsters.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/uploads")
@RequiredArgsConstructor
public class ImageUploadController {
    private final UserService userService;

    private String uploadDir = "src/main/resources/images";

    @PostMapping("/images")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        return userService.uploadImage(file);
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        return userService.getImage(filename);
    }
}
