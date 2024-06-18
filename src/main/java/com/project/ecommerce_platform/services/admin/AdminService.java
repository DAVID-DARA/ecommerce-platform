package com.project.ecommerce_platform.services.admin;

import com.project.ecommerce_platform.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {

    List<User> getAllUsers();

    ResponseEntity<Void> deleteAllUSer ();
}
