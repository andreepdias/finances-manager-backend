package com.github.andreepdias.mymoney.service;

import com.github.andreepdias.mymoney.exception.DuplicatedException;
import com.github.andreepdias.mymoney.model.entity.User;
import com.github.andreepdias.mymoney.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    private User getUserByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " was not found."));
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserByEmail(email);

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    public User insert(User user){
        boolean exists = repository.existsByEmail(user.getEmail());
        if(exists){
            throw new DuplicatedException("User email already exists.");
        }
        user.setId(null);
        return repository.save(user);
    }

    public Integer findAuthenticatedUserId(){
        String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getUserByEmail(email).getId();
    }

    public User findAuthenticatedUser(){
        String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getUserByEmail(email);
    }
}
