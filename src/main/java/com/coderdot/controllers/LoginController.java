package com.coderdot.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderdot.dto.LoginRequest;
import com.coderdot.dto.LoginResponse;
import com.coderdot.services.jwt.CustomerServiceImpl;
import com.coderdot.utils.JwtUtil;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final CustomerServiceImpl customerService;

    private final JwtUtil jwtUtil;


    @Autowired
    public LoginController(AuthenticationManager authenticationManager, CustomerServiceImpl customerService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            // Load user details
            final UserDetails userDetails = customerService.loadUserByUsername(loginRequest.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());

            // Return success response
            return ResponseEntity.ok(new LoginResponse(jwt));

        } catch (BadCredentialsException e) {
            // Return 401 Unauthorized with error message
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Incorrect email or password.");
            errorResponse.put("status", HttpStatus.UNAUTHORIZED.value());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);

        } catch (DisabledException disabledException) {
            // Return 403 Forbidden for disabled accounts
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Customer account is not activated.");
            errorResponse.put("status", HttpStatus.FORBIDDEN.value());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }
    }


}
