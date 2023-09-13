package com.backend.backend.controller;

import com.backend.backend.entities.Comptes;
import com.backend.backend.entities.Consultants;
import com.backend.backend.entities.Roles;
import com.backend.backend.services.ICompteService;
import com.backend.backend.services.IConsultantService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping("/")
@SessionAttributes({ "user_id", "user_name", "nom", "prenome" })
public class SessionController {
    @Autowired
    IConsultantService consultantService;
//    @PostMapping("/api/set-profile")
//    public ResponseEntity<String> setProfile(HttpSession session, @RequestBody Consultants profileData) {
//        // Assuming ProfileData is a Java class representing the profile information
//
//        session.setAttribute("user_id", profileData.getId_c());
//        session.setAttribute("user_name", profileData.getEmailc());
//        consultantService.AjouterConsultant(profileData);
//        // You can set other profile attributes as needed
//        return ResponseEntity.ok("Profile information set successfully!" +session);
//    }
    @GetMapping("/api/get-profile")
    public ResponseEntity<Comptes> getProfile(HttpSession session) {
        Comptes profileData = new Comptes();
        profileData.setIdc((Long) session.getAttribute("user_id"));
        profileData.setEmailc((String) session.getAttribute("user_name"));
        profileData.setNom_c((String) session.getAttribute("nom"));
        profileData.setPrenom_c((String) session.getAttribute("prenom"));
        profileData.setRoles((Roles) session.getAttribute("roles"));
        // Get other profile attributes and set them in the ProfileData object

        return  ResponseEntity.ok(profileData);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session, HttpServletResponse response) {
        // Invalidate the session on the server-side
        session.invalidate();

        // Clear the session cookie from the browser by setting an expired cookie
        Cookie sessionCookie = new Cookie("JSESSIONID", "");
        sessionCookie.setMaxAge(0); // Set cookie expiration to zero to clear it
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);

        return ResponseEntity.status(HttpStatus.OK).body("Logout successful");
    }
}


