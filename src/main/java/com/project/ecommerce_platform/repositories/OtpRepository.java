package com.project.ecommerce_platform.repositories;

import com.project.ecommerce_platform.entities.Otp;
import com.project.ecommerce_platform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OtpRepository extends JpaRepository<Otp, UUID> {

    Optional<Otp> findByUser(User user);
}
