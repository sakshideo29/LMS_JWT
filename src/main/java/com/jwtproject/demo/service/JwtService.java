package com.jwtproject.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtService {

    @Autowired
    private UserInfoService userDetailsService;

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"; // Secure key for signing JWT

    // Generate a new access token (short-lived)
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    // Generate a refresh token (long-lived)
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))  // Refresh token expires in 7 days
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    // Create a JWT token with specified claims and subject (username)
    private String createToken(Map<String, Object> claims, String userName) {
        // Fetch the roles from the UserInfoService
        String roles = userDetailsService.loadUserByUsername(userName).getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); // Collect roles as a comma-separated string

        // Add roles to the claims
        claims.put("roles", roles);  // Add roles claim to the JWT payload

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Token valid for 1 hour
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Get the signing key for JWT token
    public Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extract the username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract the expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract a claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from the token
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .setAllowedClockSkewSeconds(120) // Allowing 2 minutes of clock skew
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Check if the token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validate the token against user details and expiration
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        if (username.equals(userDetails.getUsername())) {
            if (isTokenExpired(token)) {
                // Token is expired, initiate the refresh token process
                return false; // Return false, so the refresh token process can take place
            }
            return true;
        }
        return false;
    }

    // Refresh Access Token if the refresh token is valid
    public String refreshAccessToken(String refreshToken) {
        // Check if the refresh token is valid
        if (!isRefreshTokenExpired(refreshToken)) {
            // Extract user info from the refresh token (if valid)
            String username = extractUsername(refreshToken);
            // Generate a new access token
            return generateToken(username); // Generate and return a new short-lived access token
        } else {
            // If refresh token is expired, handle re-authentication or re-issuance
            throw new RuntimeException("Refresh token expired. Please log in again.");
        }
    }

    // Check if the refresh token is expired
    public Boolean isRefreshTokenExpired(String refreshToken) {
        try {
            Claims claims = extractAllClaims(refreshToken);
            Date expirationDate = claims.getExpiration();
            return expirationDate.before(new Date());
        } catch (Exception e) {
            return true; // If there's an error parsing, treat as expired
        }
    }

    // Handle token expiration errors gracefully and throw custom exception if necessary
    public String extractUsernameSafely(String token) {
        try {
            return extractUsername(token);
        } catch (ExpiredJwtException e) {
            // Token expired, log it or handle refresh mechanism
            throw new RuntimeException("JWT token expired. Please re-authenticate.");
        } catch (Exception e) {
            throw new RuntimeException("Error extracting username from token.");
        }
    }
}





//package com.jwtproject.demo.service;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//@Component
//public class JwtService {
//
////	@Autowired
////    private UserInfoService userDetailsService;
////	
////    // Replace this with a secure key in a real application, ideally fetched from environment variables
////    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
////
////    // Generate token with given user name
////    public String generateToken(String userName) {
////        Map<String, Object> claims = new HashMap<>();
////        return createToken(claims, userName);
////    }
////    public String generateRefreshToken(String username) {
////        return Jwts.builder()
////                .setSubject(username)
////                .setIssuedAt(new Date())
////                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 10))  // Refresh token expires in 7 days
////                .signWith(SignatureAlgorithm.HS256, SECRET)
////                .compact();
////    }
////
////
////    // Create a JWT token with specified claims and subject (user name)
//////    private String createToken(Map<String, Object> claims, String userName) {
//////        return Jwts.builder()
//////                .setClaims(claims)
//////                .setSubject(userName)
//////                .setIssuedAt(new Date())
//////                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Token valid for 30 minutes
//////                .signWith(getSignKey(), SignatureAlgorithm.HS256)
//////                .compact();
//////    }
////    
//// 
////        private String createToken(Map<String, Object> claims, String userName) {
////            // Fetch the roles from the UserInfoService
////            String roles = userDetailsService.loadUserByUsername(userName).getAuthorities().stream()
////                    .map(GrantedAuthority::getAuthority)
////                    .collect(Collectors.joining(",")); // Collect roles as a comma-separated string
////
////            // Add roles to the claims
////            claims.put("roles", roles);  // Add roles claim to the JWT payload
////
////            return Jwts.builder()
////                    .setClaims(claims)
////                    .setSubject(userName)
////                    .setIssuedAt(new Date())
////                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Token valid for 30 minutes
////                    .signWith(getSignKey(), SignatureAlgorithm.HS256)
////                    .compact();
////        }
////
////        // Get the signing key for JWT token
////        public Key getSignKey() {
////            byte[] keyBytes = Decoders.BASE64.decode(SECRET);
////            return Keys.hmacShaKeyFor(keyBytes);
////        }
////
////        // Extract the username from the token
////        public String extractUsername(String token) {
////            return extractClaim(token, Claims::getSubject);
////        }
////
////        // Extract the expiration date from the token
////        public Date extractExpiration(String token) {
////            return extractClaim(token, Claims::getExpiration);
////        }
////
////        // Extract a claim from the token
////        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
////            final Claims claims = extractAllClaims(token);
////            return claimsResolver.apply(claims);
////        }
////
////        // Extract all claims from the token
////        public Claims extractAllClaims(String token) {
////            return Jwts.parserBuilder()
////                    .setSigningKey(getSignKey())
////                    .build()
////                    .parseClaimsJws(token)
////                    .getBody();
////        }
////
////        // Check if the token is expired
////        private Boolean isTokenExpired(String token) {
////            return extractExpiration(token).before(new Date());
////        }
////
////        // Validate the token against user details and expiration
////        public Boolean validateToken(String token, UserDetails userDetails) {
////            final String username = extractUsername(token);
////            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
////        }
////    }
//
//
//
//	    @Autowired
//	    private UserInfoService userDetailsService;
//
//	    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"; // Secure key for signing JWT
//
//	    // Generate a new access token (short-lived)
//	    public String generateToken(String userName) {
//	        Map<String, Object> claims = new HashMap<>();
//	        return createToken(claims, userName);
//	    }
//
//	    // Generate a refresh token (long-lived)
//	    public String generateRefreshToken(String username) {
//	        return Jwts.builder()
//	                .setSubject(username)
//	                .setIssuedAt(new Date())
//	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))  // Refresh token expires in 7 days
//	                .signWith(SignatureAlgorithm.HS256, SECRET)
//	                .compact();
//	    }
//
//	    // Create a JWT token with specified claims and subject (username)
//	    private String createToken(Map<String, Object> claims, String userName) {
//	        // Fetch the roles from the UserInfoService
//	        String roles = userDetailsService.loadUserByUsername(userName).getAuthorities().stream()
//	                .map(GrantedAuthority::getAuthority)
//	                .collect(Collectors.joining(",")); // Collect roles as a comma-separated string
//
//	        // Add roles to the claims
//	        claims.put("roles", roles);  // Add roles claim to the JWT payload
//
//	        return Jwts.builder()
//	                .setClaims(claims)
//	                .setSubject(userName)
//	                .setIssuedAt(new Date())
//	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Token valid for 30 minutes
//	                .signWith(getSignKey(), SignatureAlgorithm.HS256)
//	                .compact();
//	    }
//
//	    // Get the signing key for JWT token
//	    public Key getSignKey() {
//	        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//	        return Keys.hmacShaKeyFor(keyBytes);
//	    }
//
//	    // Extract the username from the token
//	    public String extractUsername(String token) {
//	        return extractClaim(token, Claims::getSubject);
//	    }
//
//	    // Extract the expiration date from the token
//	    public Date extractExpiration(String token) {
//	        return extractClaim(token, Claims::getExpiration);
//	    }
//
//	    // Extract a claim from the token
//	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//	        final Claims claims = extractAllClaims(token);
//	        return claimsResolver.apply(claims);
//	    }
//
//	    // Extract all claims from the token
//	    public Claims extractAllClaims(String token) {
//	        return Jwts.parserBuilder()
//	                .setSigningKey(getSignKey())
//	                .setAllowedClockSkewSeconds(60) // Allowing a 1-minute skew
//	                .build()
//	                .parseClaimsJws(token)
//	                .getBody();
//	    }
//
//	    // Check if the token is expired
//	    private Boolean isTokenExpired(String token) {
//	        return extractExpiration(token).before(new Date());
//	    }
//
//	    // Validate the token against user details and expiration
//	    public Boolean validateToken(String token, UserDetails userDetails) {
//	        final String username = extractUsername(token);
//	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//	    }
//
//	    // Check if the refresh token is expired
//	    public Boolean isRefreshTokenExpired(String refreshToken) {
//	        try {
//	            Claims claims = extractAllClaims(refreshToken);
//	            Date expirationDate = claims.getExpiration();
//	            return expirationDate.before(new Date());
//	        } catch (Exception e) {
//	            return true; // If there's an error parsing, treat as expired
//	        }
//	    }
//	}
//
//
//
