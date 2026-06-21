package com.typingrace.service.impl;

import com.typingrace.domain.entity.Profile;
import com.typingrace.domain.entity.User;
import com.typingrace.dto.request.LoginRequest;
import com.typingrace.dto.request.RegisterRequest;
import com.typingrace.dto.response.JwtResponse;
import com.typingrace.repository.ProfileRepository;
import com.typingrace.repository.UserRepository;
import com.typingrace.service.AuthService;
import com.typingrace.util.JwtUtils;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private  final JwtUtils jwtUtils;
    private final ProfileRepository profileRepository;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                           ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.profileRepository = profileRepository;
    }

    @Override
    public String registerUser(RegisterRequest registerRequest) {
        if(userRepository.existsByUsername(registerRequest.getUsername())){
            throw new EntityExistsException("User already registered with this username");
        }

        if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new EntityExistsException("User already registered with this Email");
        }

        User newUser = new User();
        Profile newProfile = new Profile();
        newUser.setName(registerRequest.getName());
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());

        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        newUser.setIsActive(true);
        newUser.setIsVerified(false); // Can be verified later via email link
        newUser.setLastLogin(null);

        User saved = userRepository.save(newUser);

        newProfile.setName(saved.getName());
        newProfile.setUser(saved);
        profileRepository.save(newProfile);
        return "User registered successfully!";
    }

    @Override
    public JwtResponse loginUser(LoginRequest loginRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User data validation trace lost."));
        user.setLastLogin(new Date());
        userRepository.save(user);

        String jwtToken = jwtUtils.generateToken(auth);
        return new JwtResponse(jwtToken);
    }
}
