package com.example.vkproject.services;

import com.example.vkproject.config.JwtUser;
import com.example.vkproject.dto.CategoryRatingDto;
import com.example.vkproject.dto.UserCategoryRatingDto;
import com.example.vkproject.dto.UserDto;
import com.example.vkproject.dto.UserRatingDto;
import com.example.vkproject.exceptions.InvalidPasswordException;
import com.example.vkproject.exceptions.InvalidUserInputException;
import com.example.vkproject.exceptions.UserLoginNotFoundException;
import com.example.vkproject.exceptions.UserNotFoundException;
import com.example.vkproject.models.Users;
import com.example.vkproject.repositories.UserRepository;
import com.example.vkproject.repositories.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTaskRepository userTaskRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users register(UserDto userDto) {
        // check if user with the same username already exists
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new UserNotFoundException(userDto.getId());
        }

        Users user = new Users();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        // save the user to the database

        return userRepository.save(user);
    }

    public Users update(Long userId, UserDto userDto) {
        // retrieve the user from the database by ID
        Users user = userRepository.findById(userDto.getId()).orElseThrow(() -> new UserLoginNotFoundException("User not found"));

        // update the user's fields
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());

        // save the updated user to the database
        Users savedUser = userRepository.save(user);

        // convert the saved user to a DTO and return it
        return savedUser;
    }

    public Users login(String username, String password) {
        // retrieve the user from the database by username
        Users user = userRepository.findByUsername(username).orElseThrow(() -> new InvalidUserInputException(username));

        // check if the provided password matches the hashed password in the database
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        // convert the user to a DTO and return it
        return user;
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        Users user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = this.getUserByLogin(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        } else {
            return new JwtUser(user.get());
        }
    }

    public Optional<Users> getUserByLogin(String username) {
        return userRepository.findByUsername(username);
    }

    public UserRatingDto getUserRating(Long id) {
        Integer rating = userTaskRepository.countOfUserSolvedTasks(id);
        Long total = userTaskRepository.count();
        UserRatingDto userRatingDto = new UserRatingDto();
        userRatingDto.setRating(rating);
        userRatingDto.setTotal(total);

        return userRatingDto;
    }

    public UserCategoryRatingDto getUserCategoryRating() {
        ArrayList<CategoryRatingDto> arrCategoryRating = new ArrayList<>();
        Long total = userTaskRepository.count();
        CategoryRatingDto categoryRatingDto = new CategoryRatingDto();

        categoryRatingDto.setSolved(userTaskRepository.countOfSolvedEasyTasks());
        categoryRatingDto.setTotal(total);
        arrCategoryRating.add(categoryRatingDto);


        categoryRatingDto.setSolved(userTaskRepository.countOfSolvedMediumTasks());
        categoryRatingDto.setTotal(total);
        arrCategoryRating.add(categoryRatingDto);


        categoryRatingDto.setSolved(userTaskRepository.countOfSolvedHardTasks());
        categoryRatingDto.setTotal(total);
        arrCategoryRating.add(categoryRatingDto);

        UserCategoryRatingDto userCategoryRatingDto = new UserCategoryRatingDto();
        userCategoryRatingDto.setCategoryRatings(arrCategoryRating);

        return userCategoryRatingDto;
    }
}
