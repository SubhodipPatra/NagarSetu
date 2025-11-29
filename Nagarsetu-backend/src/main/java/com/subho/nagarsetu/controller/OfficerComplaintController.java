package com.subho.nagarsetu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subho.nagarsetu.model.Complaint;
import com.subho.nagarsetu.model.ComplaintStatus;
import com.subho.nagarsetu.service.ComplaintService;

@RestController
@RequestMapping("/officer")
@PreAuthorize("hasRole('OFFICER')")
public class OfficerComplaintController {

    @Autowired
    private ComplaintService complaintService;

   
    @GetMapping("/complaints")
    @PreAuthorize("hasRole('OFFICER')")
    public ResponseEntity<List<Complaint>> getMyComplaints(Authentication authentication) {
        String email = authentication.getName();
        List<Complaint> complaints = complaintService.getComplaintsByOfficerEmail(email);
        return ResponseEntity.ok(complaints);
    }



    @PutMapping("/complaints/update/{id}")
    @PreAuthorize("hasRole('OFFICER')")
    public ResponseEntity<?> updateComplaint(
            @PathVariable Long id,
            @RequestParam ComplaintStatus status,
            @RequestParam(required = false) String remarks,
            Authentication authentication) {

        String officerEmail = authentication.getName();
        try {
            Complaint updated = complaintService.updateComplaintStatusForOfficer(id, status, remarks, officerEmail);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
