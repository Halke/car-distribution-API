package com.halks.distribution_erp.Auth;

import com.halks.distribution_erp.User.User;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("${api.version.prefix}/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("token")
    public AuthResponse getToken(@RequestBody AuthRequest authRequest) {
        return authService.verify(authRequest);
    }

    @PostMapping("register")
    public RegisterResponse register(@RequestBody User user) {
        return authService.register(user);
    }

}
