package org.example.app.controllers;

import org.example.app.ResponseApi;
import org.example.app.services.UserService;
import org.example.user.UserRequests.UserLogin;
import org.example.user.UserRequests.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRegister register) {
        try {
            if (register.getUsername() == null || register.getUsername().isBlank()) {
                return ResponseEntity.status(400)
                        .body(new ResponseApi(
                                "error",
                                "Missing field username",
                                400));
            }
            if (register.getPassword() == null || register.getPassword().isBlank()) {
                return ResponseEntity.status(400)
                        .body(new ResponseApi(
                                "error",
                                "Missing field password",
                                400));
            }
            if (register.getEmail() == null || register.getEmail().isBlank()) {
                return ResponseEntity.status(400)
                        .body(new ResponseApi(
                                "error",
                                "Missing field email",
                                400));
            }
            if (register.getRole() != null && register.getRole().isBlank()) {
                return ResponseEntity.status(400)
                        .body(new ResponseApi(
                                "error",
                                "field role is not empty",
                                400));
            }
            ResponseApi response = userService.registerUser(register);
            return ResponseEntity.status(response.getCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseApi(
                            "error",
                            e.getMessage(),
                            500));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> postMethodName(@RequestBody UserLogin login) {
        try {
            if (login.getUsername() == null || login.getUsername().isBlank()) {
                return ResponseEntity.status(400)
                        .body(new ResponseApi(
                                "error",
                                "Missing field username",
                                400));
            }
            if (login.getPassword() == null || login.getPassword().isBlank()) {
                return ResponseEntity.status(400)
                        .body(new ResponseApi(
                                "error",
                                "Missing field password",
                                400));
            }
            ResponseApi response = userService.loginUser(login);
            return ResponseEntity.status(response.getCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseApi(
                            "error",
                            e.getMessage(),
                            500));

        }
    }
}
