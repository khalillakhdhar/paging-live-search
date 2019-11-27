/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Rappel.Controller;

import com.example.Rappel.Exception.ResourceNotFoundException;
import com.example.Rappel.Model.Admin;
import com.example.Rappel.Repository.AdminRepository;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sniper Elite
 */
@RestController
@Transactional
@RequestMapping("/api")
@CrossOrigin(origins = "localhost:3000", maxAge = 3600)
public class AdminController {
     @Autowired
    AdminRepository adminRepository;

    // Get All Admins
@GetMapping("/admin")
public List<Admin> getAdmins() {
    return (List<Admin>) adminRepository.findAll();
}
// Create a new Admin
@PostMapping("/admins")
public Admin createAdmin(@Valid @RequestBody Admin admin) {
    return adminRepository.save(admin);
}

// Get a Single Admin
@GetMapping("/admins/{id}")
public Admin getAdminById(@PathVariable(value = "id") Long adminId) {
    return adminRepository.findById(adminId)
            .orElseThrow(() -> new ResourceNotFoundException("Admin", "id", adminId));
}
   
// Delete a Admin
@DeleteMapping("/admins/{id}")
public ResponseEntity<?> deleteAdmin(@PathVariable(value = "id") Long adminId) {
    Admin admin = adminRepository.findById(adminId)
            .orElseThrow(() -> new ResourceNotFoundException("Admin", "id", adminId));

    adminRepository.delete(admin);

    return ResponseEntity.ok().build();
}
}
