package com.cz1.auth;

import com.cz1.domain.Auth;
import com.cz1.domain.User;
import com.cz1.secruity.JwtTokenUtil;
import com.cz1.secruity.JwtUser;
import com.cz1.secruity.JwtUserFactory;
import com.cz1.user.UserRepository;
import com.fasterxml.jackson.annotation.JsonFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Arrays.asList;

/**
 * Created by wkchen on 2017/3/22.
 */
@Service
public class AuthServicImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private UserRepository userRepository;


    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServicImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    @JsonFilter("password")
    @Override
    public User register(User user) {
        final String username = user.getUsername();
        if (userRepository.findByUsername(username) != null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        user.setLastPasswordResetDate(new Date());
        user.setRole(asList("ROLE_USER"));
        Auth auth = new Auth();
        auth.setToken(jwtTokenUtil.generatClaims(JwtUserFactory.create(user)));
        user.setAuth(auth);
        return userRepository.insert(user);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generatClaims(userDetails);
        return token;
    }

    @Override
    public String reftrsh(String oldToken) {
        final String token = oldToken;
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
