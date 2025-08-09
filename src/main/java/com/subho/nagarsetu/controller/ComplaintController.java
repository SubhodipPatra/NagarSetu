package com.subho.nagarsetu.controller;

import com.subho.nagarsetu.dto.ComplaintResponseDTO;
import com.subho.nagarsetu.model.Complaint;
import com.subho.nagarsetu.model.Officer;
import com.subho.nagarsetu.service.ComplaintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    // 🔐 Submit complaint — Only USER
    @PostMapping("/submit")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> submitComplaint(@RequestBody Complaint complaint, Authentication auth) {
        try {
            complaint.setUserEmail(auth.getName());
            Complaint saved = complaintService.submitComplaint(complaint);
            Officer officer = saved.getOfficer();

            ComplaintResponseDTO response = new ComplaintResponseDTO(
                    saved.getId(),
                    saved.getDescription(),
                    saved.getType(),
                    saved.getUserPincode(),
                    officer.getName(),
                    officer.getDepartment(),
                    officer.getLocation()
            );

            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("⚠️ Internal server error");
        }
    }

    // 🔐 Admin-only: Get all complaints
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    // 🔐 User: View own complaints
    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public List<Complaint> getMyComplaints(Authentication auth) {
        return complaintService.getComplaintsByUserEmail(auth.getName());
    }

    // 🔐 Admin or owner: View one complaint
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getComplaint(@PathVariable Long id, Authentication auth) {
        Complaint complaint = complaintService.getComplaintById(id);
        if (complaint == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Complaint not found");
        }

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(granted -> granted.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !complaint.getUserEmail().equals(auth.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌ Unauthorized access");
        }

        return ResponseEntity.ok(complaint);

    }

    // 🔐 Admin-only: Delete a complaint by ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteComplaint(@PathVariable Long id) {
        try {
            boolean deleted = complaintService.deleteComplaintById(id);
            if (deleted) {
                return ResponseEntity.ok("✅ Complaint deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Complaint not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("⚠️ Failed to delete complaint");
        }
    }

}
