package com.subho.nagarsetu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subho.nagarsetu.model.Complaint;
import com.subho.nagarsetu.model.Officer;
import com.subho.nagarsetu.service.ComplaintService;
import com.subho.nagarsetu.service.OfficerService;

@RestController
@RequestMapping("/officers")
public class OfficerController {

    @Autowired
    private OfficerService officerService;

    @PostMapping("/register")
    public Officer registerOfficer(@RequestBody Officer officer) {
        return officerService.registerOfficer(officer);
    }

    @GetMapping
    public List<Officer> getAllOfficers() {
        return officerService.getAllOfficers();
    }

    @GetMapping("/{id}")
    public Officer getOfficer(@PathVariable Long id) {
        return officerService.getOfficerById(id).orElse(null);
    }


    @GetMapping("/approved")
    public List<Officer> getApproved(@RequestParam String department) {
        return officerService.getApprovedByDepartment(department);
    }
    @Autowired
    private ComplaintService complaintService;


    @GetMapping("/complaints/{department}/{pincode}")
    public List<Complaint> getComplaintsByDeptAndPincode(
            @PathVariable String department,
            @PathVariable String pincode) {

        return complaintService.getComplaintsByDeptAndPincode(department, pincode);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('OFFICER')")
    public ResponseEntity<?> getOfficerDetails(Authentication auth) {
        String email = auth.getName(); 
        Officer officer = officerService.getOfficerByEmail(email);
        return ResponseEntity.ok(officer);
    }

}
