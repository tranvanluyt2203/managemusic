package org.example.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;

import org.example.app.ResponseApi;
import org.example.app.services.UserService;
import org.example.security.JWT.JwtFilter;
import org.example.security.JWT.JwtUtil;
import org.example.user.entities.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private JwtUtil jwtUtil;
    private String token;

    private ResponseEntity<Object> ProcessErrorToken(HttpServletRequest request) {
        try {
            String token = jwtFilter.getToken(request);
            if (token == null || !jwtUtil.validateToken(token, jwtUtil.extractUsername(token))) {
                return ResponseEntity.status(401)
                        .body(new ResponseApi(
                                "error",
                                "Unauthorization",
                                401));
            }
            this.token = token;
            return null;
        } catch (SignatureException e) {
            return ResponseEntity.status(500)
                    .body(new ResponseApi(
                            "error",
                            e.getMessage(),
                            401));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseApi(
                            "error",
                            e.getMessage(),
                            500));
        }
    }

    @GetMapping("/get_profile")
    public ResponseEntity<Object> getMethodName(HttpServletRequest request) {
        ResponseEntity<Object> processErrorToken = ProcessErrorToken(request);
        if (processErrorToken == null) {
            ResponseApi response = userService.getProfileUser(jwtUtil.extractUsername(token));
            return ResponseEntity.status(response.getCode()).body(response);
        }
        return processErrorToken;

    }

    @PutMapping("/update_profile")
    public ResponseEntity<Object> putMethodName(HttpServletRequest request, @RequestBody Profile profile) {
        if (profile.getAddress() != null && profile.getAddress().isBlank()) {
            return ResponseEntity.status(400)
                    .body(new ResponseApi(
                            "error",
                            "Field address is not empty",
                            400));
        }
        if (profile.getFullName() != null && profile.getFullName().isBlank()) {
            return ResponseEntity.status(400)
                    .body(new ResponseApi(
                            "error",
                            "Field fullname is not empty",
                            400));
        }
        if (profile.getPhoneNumber() != null && profile.getPhoneNumber().isBlank()) {
            return ResponseEntity.status(400)
                    .body(new ResponseApi(
                            "error",
                            "Field phonenumber is not empty",
                            400));
        }
        ResponseEntity<Object> processErrorToken = ProcessErrorToken(request);
        if (processErrorToken == null) {
            ResponseApi response = userService.updateProfileUser(jwtUtil.extractUsername(token), profile);
            return ResponseEntity.status(response.getCode()).body(response);
        }
        return processErrorToken;
    }

    @DeleteMapping("/delete_account")
    public ResponseEntity<Object> deleteProfile(HttpServletRequest request) {
        ResponseEntity<Object> processErrorToken = ProcessErrorToken(request);
        if (processErrorToken == null) {
            ResponseApi response = userService.deleteUser(jwtUtil.extractUsername(token));
            return ResponseEntity.status(response.getCode()).body(response);
        }
        return processErrorToken;
    }

}
