package com.subho.nagarsetu.controller;

import com.subho.nagarsetu.model.Complaint;
import com.subho.nagarsetu.model.ComplaintStatus;
import com.subho.nagarsetu.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/officer")
@PreAuthorize("hasRole('OFFICER')")
public class OfficerComplaintController {

    @Autowired
    private ComplaintService complaintService;

    /**
     * 🔐 Get complaints assigned to logged-in officer
     */
    @GetMapping("/complaints")
    @PreAuthorize("hasRole('OFFICER')")
    public ResponseEntity<List<Complaint>> getMyComplaints(Authentication authentication) {
        String email = authentication.getName();
        List<Complaint> complaints = complaintService.getComplaintsByOfficerEmail(email);
        return ResponseEntity.ok(complaints);
    }

    /**
     * 🔐 Update complaint if it belongs to the logged-in officer
     */

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
