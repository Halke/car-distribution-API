package com.halks.distribution_erp.Auth;

import com.halks.distribution_erp.Exception.UserNotFoundException;
import com.halks.distribution_erp.Security.JWT.JWTService;
import com.halks.distribution_erp.User.User;
import com.halks.distribution_erp.User.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepo userRepo,
                       AuthenticationManager authenticationManager,
                       JWTService jwtService,
                       PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponse register(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User newUser = userRepo.save(user);

        return new RegisterResponse(
                newUser.getId(),
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getFirstName(),
                newUser.getLastName()
        );
    }

    public AuthResponse verify(AuthRequest request) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(request.username());

                return new AuthResponse(token);
            }

            throw new UserNotFoundException("Invalid username or password");

        } catch (BadCredentialsException | UsernameNotFoundException ex) {
            throw new UserNotFoundException("Invalid username or password");
        } catch (AuthenticationException ex) {
            throw new UserNotFoundException("Authentication failed");
        }
    }

}
