package com.jwtproject.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtproject.demo.entity.AuthRequest;
import com.jwtproject.demo.entity.UserInfo;
import com.jwtproject.demo.exception.CustomException;
import com.jwtproject.demo.service.JwtService;
import com.jwtproject.demo.service.UserInfoService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;



@RestController
@RequestMapping("/auth")


public class UserController {

//    @Autowired
//    private UserInfoService service;
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    
//    @GetMapping("/welcome")
//    public String welcome() {
//        return "Welcome this endpoint is not secure";
//    }
//    @PostMapping("/refreshToken")
//    public String refreshAccessToken(@RequestBody String refreshToken) {
//        try {
//            Claims claims = jwtService.extractAllClaims(refreshToken);  // Extract claims from the refresh token
//            String username = claims.getSubject();
//
//            // Validate refresh token, and if valid, generate a new access token
//            if (isValidRefreshToken(refreshToken)) {
//                return jwtService.generateToken(username);
//            } else {
//                throw new UsernameNotFoundException("Invalid refresh token!");
//            }
//        } catch (ExpiredJwtException e) {
//            throw new CustomException("Refresh token expired. Please login again.");
//        } catch (Exception e) {
//            throw new CustomException("Error processing refresh token.");
//        }
//    }
//
//    private boolean isValidRefreshToken(String refreshToken) {
//        // Here, you can check if the refresh token is valid, e.g., by checking if it exists in your database
//        return true;
//    }
//
//
//    @PostMapping("/addNewUser")
//    public String addNewUser(@RequestBody UserInfo userInfo) {
//        return service.addUser(userInfo);
//    }
//
//    @GetMapping("/user/userProfile")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public String userProfile() {
//        return "Welcome to User Profile";
//    }
//
//    @GetMapping("/admin/adminProfile")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public String adminProfile() {
//        return "Welcome to Admin Profile";
//    }
//
//    @PostMapping("/generateToken")
//    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//    	  System.out.println("Authenticating user: " + authRequest.getUsername());
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//        );
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(authRequest.getUsername());
//        } else {
//            throw new UsernameNotFoundException("Invalid user request!");
//        }
//    }
//}
	





	    @Autowired
	    private UserInfoService service;

	    @Autowired
	    private JwtService jwtService;

	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @GetMapping("/welcome")
	    public String welcome() {
	        return "Welcome! This endpoint is not secure.";
	    }

	    // Refresh Access Token using refresh token
	    @PostMapping("/refreshToken")
	    public String refreshAccessToken(@RequestBody String refreshToken) {
	        try {
	            // Check if the refresh token is expired
	            if (jwtService.isRefreshTokenExpired(refreshToken)) {
	                throw new CustomException("Refresh token expired. Please login again.");
	            }

	            // Extract claims from the refresh token and get the username
	            Claims claims = jwtService.extractAllClaims(refreshToken);
	            String username = claims.getSubject();

	            // Generate new access token based on refresh token
	            return jwtService.generateToken(username);

	        } catch (ExpiredJwtException e) {
	            throw new CustomException("Refresh token expired. Please login again.");
	        } catch (Exception e) {
	            throw new CustomException("Error processing refresh token.");
	        }
	    }

	    // Endpoint to add a new user
	    @PostMapping("/addNewUser")
	    public String addNewUser(@RequestBody UserInfo userInfo) {
	        return service.addUser(userInfo);
	    }

	    // User profile endpoint with role-based authorization
	    @GetMapping("/user/userProfile")
	    @PreAuthorize("hasAuthority('ROLE_USER')")
	    public String userProfile() {
	        return "Welcome to User Profile!";
	    }

	    // Admin profile endpoint with role-based authorization
	    @GetMapping("/admin/adminProfile")
	    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	    public String adminProfile() {
	        return "Welcome to Admin Profile!";
	    }

	    // Authenticate and generate token
	    @PostMapping("/generateToken")
	    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	        );
	        if (authentication.isAuthenticated()) {
	            return jwtService.generateToken(authRequest.getUsername());
	        } else {
	            throw new UsernameNotFoundException("Invalid user request!");
	        }
	    }
	}
