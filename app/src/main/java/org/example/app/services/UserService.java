package org.example.app.services;

import org.example.security.JWT.JwtUtil;
import org.example.security.exceptions.NotFound;
import org.example.security.repositories.RoleRepository;

import java.util.HashMap;

import org.example.app.ResponseApi;
import org.example.user.UserRequests.UserLogin;
import org.example.user.UserRequests.UserRegister;
import org.example.user.dtos.ProfileDTO;
import org.example.user.dtos.UserDTO;
import org.example.user.entities.Profile;
import org.example.user.entities.User;
import org.example.user.repositories.ProfileRepository;
import org.example.user.repositories.UserRepository;
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
    @Autowired
    private ProfileRepository profileRepository;

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
            Profile profile = profileRepository.save(new Profile());
            user.setProfile(profile);
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

    public ResponseApi getProfileUser(String username) {
        User user = getUserByUsername(username);
        ProfileDTO profileDTO = new ProfileDTO(user, user.getProfile());
        return new ResponseApi(
                "success",
                "Get profile success",
                200,
                profileDTO);

    }

    public ResponseApi updateProfileUser(String username, Profile update) {
        User user = getUserByUsername(username);
        Profile profile = getProfileById(user.getProfile().getId());
        profile.setAddress(update.getAddress());
        profile.setFullName(update.getFullName());
        profile.setImage(update.getImage());
        profile.setPhoneNumber(update.getPhoneNumber());
        profileRepository.save(profile);
        return new ResponseApi(
                "success",
                "Update profile success",
                200);
    }

    public ResponseApi deleteUser(String username) {
        User user = getUserByUsername(username);
        userRepository.delete(user);
        return new ResponseApi(
                "success",
                "Delete user success",
                200);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFound("User with id " + id + " not found"));
    }

    public Profile getProfileById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new NotFound("Profile with id " + id + " not found"));
    }

}