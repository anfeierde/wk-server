package com.cz1.auth;

import com.cz1.domain.User;
import com.cz1.secruity.JwtAuthenticationResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wkchen on 2017/3/22.
 */
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @GetMapping(value = "/auth/{username}")
    public ResponseEntity<?> createAuthenticationToken(
            @PathVariable("username")String username,@RequestParam("password")String password) throws AuthenticationException {
        final String token = authService.login(username,password);
        return ResponseEntity.ok(new JwtAuthenticationResponce(token));
    }

    @GetMapping(value = "${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest httpServletRequest) throws AuthenticationException {
        String token = httpServletRequest.getHeader(tokenHeader);
        String refreshedToken = authService.reftrsh(token);
        if (refreshedToken == null) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.ok(refreshedToken);
        }
    }

    @PostMapping(value = "${jwt.route.authentication.register}")
    public User register(@RequestBody User user) throws AuthenticationException {
        return authService.register(user);
    }
}
