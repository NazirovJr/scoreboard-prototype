package com.example.vkproject.controllers;

import com.example.vkproject.dto.LoginDto;
import com.example.vkproject.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

//        @GetMapping("/login")
//        public String showLoginForm() {
//            return "login";
//        }
//
//        @PostMapping("/login")
//        public String login(LoginDto model) {
//            System.out.println(model);
//            return "login";
//        }
}



