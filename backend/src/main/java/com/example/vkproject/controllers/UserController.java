package com.example.vkproject.controllers;

import com.example.vkproject.dto.UserCategoryRatingDto;
import com.example.vkproject.dto.UserDto;
import com.example.vkproject.dto.UserRatingDto;
import com.example.vkproject.models.Users;
import com.example.vkproject.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register( @RequestBody UserDto request) {
        Users userDto = userService.register(request);
        return ResponseEntity.ok(userDto);
    }

//    @PostMapping("/login")
//    public ResponseEntity<TokenDto> login(@Valid @RequestBody UserLoginRequest request) {
//        TokenDto tokenDto = userService.login(request);
//        return ResponseEntity.ok(tokenDto);
//    }

    @GetMapping("/login/{login}")
    public ResponseEntity<Users> getByLogin(@PathVariable String login) {
        return ResponseEntity.ok(userService.getUserByLogin(login).get());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        Users user = userService.update(userId, userDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/rating/{id}")
    public ResponseEntity<UserRatingDto> getUserRating(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserRating(id));
    }

    @GetMapping("/category/rating")
    public ResponseEntity<UserCategoryRatingDto> getUserCategoryRating() {
        return ResponseEntity.ok(userService.getUserCategoryRating());
    }
}
