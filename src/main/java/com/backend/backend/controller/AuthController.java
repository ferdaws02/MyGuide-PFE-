package com.backend.backend.controller;

import com.backend.backend.Repositories.IComptesRepository;



import com.backend.backend.dto.LoginRequest;
import com.backend.backend.entities.Comptes;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
@SessionAttributes({ "user_id", "user_name", "nom", "prenome" })


public class AuthController {
    @Autowired
    private IComptesRepository userRepository;






    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(HttpSession session, HttpServletResponse response, @RequestBody LoginRequest loginRequest) {
        Comptes user = userRepository.findByEmailc(loginRequest.getUsername());

        if (user == null || !user.getMdp_c().equals(loginRequest.getPassword())) {
            // Handle invalid login credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(createErrorResponse("Invalid username or password"));
        }

        // Set user information in the session
        session.setAttribute("user_id", user.getIdc());
        session.setAttribute("user_name", user.getEmailc());
        session.setAttribute("nom", user.getNom_c());
        session.setAttribute("prenom", user.getPrenom_c());
        session.setAttribute("roles", user.getRoles());
        // Set the session ID as a cookie
        String sessionId = session.getId();
        Cookie sessionCookie = new Cookie("JSESSIONID", sessionId);
        sessionCookie.setPath("/"); // Set the cookie path to root so it's accessible on all paths
        sessionCookie.setHttpOnly(true); // Make the cookie accessible only by the server-side
        // Optionally set additional cookie attributes such as 'domain', 'secure', and 'maxAge'

        // Add the session cookie to the HTTP response
        response.addCookie(sessionCookie);

        // Return a success response along with the session information
        return ResponseEntity.ok(createSuccessResponse("Login successful!", sessionId));
    }

    private Map<String, String> createSuccessResponse(String message, String sessionId) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", message);
        response.put("session_id", sessionId);
        return response;
    }

    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        return response;

    }
}
