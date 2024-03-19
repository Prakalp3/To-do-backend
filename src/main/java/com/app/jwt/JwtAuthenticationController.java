package com.app.jwt;

import com.app.user.model.UserInfo;
import com.app.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class JwtAuthenticationController {
    
    private final JwtService jwtService;
    
    private final AuthenticationManager authenticationManager;

    private UserInfoService userInfoService;

    public JwtAuthenticationController(JwtService jwtService, AuthenticationManager authenticationManager, UserInfoService userInfoService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userInfoService = userInfoService;
    }



        @GetMapping("/welcome")
        public String welcome() {
            return "Welcome this endpoint is not secure";
        }

        @PostMapping("/addNewUser")
        public String addNewUser(@RequestBody UserInfo userInfo) {
            return userInfoService.addUser(userInfo);
        }

        @GetMapping("/user/userProfile")
        @PreAuthorize("hasAuthority('ROLE_USER')")
        public String userProfile() {
            return "Welcome to User Profile";
        }

        @GetMapping("/admin/adminProfile")
        @PreAuthorize("hasAuthority('ROLE_ADMIN')")
        public String adminProfile() {
            return "Welcome to Admin Profile";
        }

        @PostMapping("/authenticate")
        public ResponseEntity<JwtTokenResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                var token= jwtService.generateToken(authRequest.getUsername());
                return ResponseEntity.ok(new JwtTokenResponse(token));
            } else {
                throw new UsernameNotFoundException("invalid user request !");
            }
        }

    }




