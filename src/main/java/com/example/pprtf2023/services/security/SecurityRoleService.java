package com.example.pprtf2023.services.security;

import com.example.pprtf2023.exceptions.RoleNotFoundException;
import com.example.pprtf2023.models.entities.Role;
import com.example.pprtf2023.repositopries.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityRoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public SecurityRoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name){
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException(name));
    }
}
