package com.project.ecommerce_platform.controllers;

import com.project.ecommerce_platform.entities.User;
import com.project.ecommerce_platform.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/get_all_users")
    public ResponseEntity<List<User>> getAllUsers () {
        List<User> ALL_USERS = adminService.getAllUsers();
        return new ResponseEntity<>(ALL_USERS, HttpStatus.OK);
    }
}
