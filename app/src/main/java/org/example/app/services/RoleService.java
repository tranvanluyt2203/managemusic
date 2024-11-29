package org.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.security.security.Security;
import org.example.user.entities.User;
import org.example.user.repositories.UserRepository;

@Service
public class RoleService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Security security;

    public List<String> getAllPermissionByUsername(String username) {
        User user = userRepository.findByUsername(username);
        List<String> allPermission = security.getListPermissions(user.getRole().getName());
        return allPermission;
    }

    public boolean hasPermission(String username, String permission) {
        return getAllPermissionByUsername(username).contains(permission);
    }

}
