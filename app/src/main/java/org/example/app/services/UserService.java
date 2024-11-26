package org.example.app.services;

import org.example.security.RoleRepository;
import org.example.security.JWT.JwtUtil;
import org.example.security.exceptions.UserNotFound;

import java.util.HashMap;

import org.example.app.ResponseApi;
import org.example.user.User;
import org.example.user.UserDTO;
import org.example.user.UserRepository;
import org.example.user.UserRequests.UserLogin;
import org.example.user.UserRequests.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public ResponseApi registerUser(UserRegister register) {
        try {
            User user = new User(register);
            if (userRepository.existsByUsername(user.getUsername())) {
                return new ResponseApi(
                        "error",
                        "Username is exists",
                        400);
            }
            if (userRepository.existsByEmail(user.getEmail())) {
                return new ResponseApi(
                        "error",
                        "Email is exists",
                        400);
            }
            if (register.getRole() == null) {
                user.setRole(roleRepository.findByName("user"));
            } else {
                if (roleRepository.findByName(register.getRole()) == null) {
                    return new ResponseApi(
                            "error",
                            "Role is not found",
                            404);
                }
                user.setRole((roleRepository.findByName(register.getRole())));
            }

            user.setPassword(passwordEncoder.encode(register.getPassword()));

            User userRegistered = userRepository.save(user);

            return new ResponseApi(
                    "success",
                    "Register Success",
                    201,
                    new UserDTO(userRegistered));
        } catch (Exception e) {
            return new ResponseApi(
                    "error",
                    e.getMessage(),
                    500);
        }

    }

    public ResponseApi loginUser(UserLogin login) {
        try {
            User user = getUserByUsername(login.getUsername());
            System.out.println(user);
            if (user == null) {
                return new ResponseApi("error", "Username is not exists", 401);
            }
            if (passwordEncoder.matches(login.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getUsername());
                HashMap<String, Object> data = new HashMap<>();
                data.put("token", token);
                data.put("user", new UserDTO(user));
                return new ResponseApi(
                        "success",
                        "Login success",
                        200,
                        data);
            } else {
                return new ResponseApi(
                        "error",
                        "Invalid password",
                        401);
            }
        } catch (Exception e) {
            return new ResponseApi(
                    "error",
                    e.getMessage(),
                    500);
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("User with id " + id + " not found"));
    }

}