package org.example.user;

import org.example.security.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseApi registerUser(UserRegister register) {
        User user = new User(register);
        if (register.getRole() == null) {
            user.setRole(roleRepository.findByName("user"));
        } else {
            user.setRole((roleRepository.findByName(register.getRole())));
        }

        user.setPassword(passwordEncoder.encode(register.getPassword()));

        User userRegistered = userRepository.save(user);

        return new ResponseApi("success", "Register Success", 201, new UserDTO(userRegistered));

    }
}