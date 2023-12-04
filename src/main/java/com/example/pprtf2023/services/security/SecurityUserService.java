package com.example.pprtf2023.services.security;

import com.example.pprtf2023.exceptions.UserAlreadyExistException;
import com.example.pprtf2023.models.dtos.request.CreateNewUserRequest;
import com.example.pprtf2023.models.entities.User;
import com.example.pprtf2023.repositopries.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {
    private final SecurityRoleService securityRoleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User '%s' not found", username))
                );
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getName()))
                        .collect(Collectors.toList())
        );
    }

    public void createNewUser(CreateNewUserRequest request){
        String userName = request.getUserName();
        User user = userRepository.findByUserName(userName)
                .orElse(null);
        if (user != null){
            throw new UserAlreadyExistException(userName);
        }
        user = new User(userName,
                request.getEmail(),
                request.getSureName(),
                request.getName(),
                passwordEncoder.encode(request.getPassword())
        );
        user.setRoles(Set.of(securityRoleService.findByName("ROLE_USER")));
        userRepository.save(user);
    }
}
