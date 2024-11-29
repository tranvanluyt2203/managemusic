package org.example.security.security;

import java.util.List;

import org.example.security.entities.Permission;
import org.example.security.entities.Role;
import org.example.security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Security {
    @Autowired
    private RoleRepository roleRepository;
    
    public List<String> getListPermissions(String name) {
        Role role = roleRepository.findByName(name);
        if (role != null) {
            return role.getPermissions().stream().map(Permission::getPermission).toList();
        }
        return null;
    }

    public boolean hasPermission(String roleName, String permission) {
        List<String> permissions = getListPermissions(roleName);
        if (permissions != null) {
            return permissions.contains(permission);
        }
        return false;
    }

    public boolean hasPermission(List<String> roleNames, String permission) {
        for (String roleName : roleNames) {
            if (hasPermission(roleName, permission)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAllPermissions(List<String> userRoles, List<String> requiredPermissions) {
        for (String requiredPermission : requiredPermissions) {
            if (!hasPermission(userRoles, requiredPermission)) {
                return false;
            }
        }
        return true;
    }
    
}
