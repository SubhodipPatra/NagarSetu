package com.subho.nagarsetu.controller;

import com.subho.nagarsetu.model.Complaint;
import com.subho.nagarsetu.model.Officer;
import com.subho.nagarsetu.service.ComplaintService;
import com.subho.nagarsetu.service.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OfficerService officerService;
    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/officers/pending")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getPendingOfficers(Authentication auth) {
        return ResponseEntity.ok(officerService.getOfficersByApproval(false));
    }

    @GetMapping("/officers/approved")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getApprovedOfficers(Authentication auth) {
        return ResponseEntity.ok(officerService.getOfficersByApproval(true));
    }

    @GetMapping("/officers/by-department")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOfficersByDept(@RequestParam String department, Authentication auth) {
        return ResponseEntity.ok(officerService.getOfficersByDepartment(department));
    }

    @PutMapping("/officers/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveOfficer(@PathVariable Long id, Authentication auth) {
        return ResponseEntity.ok(officerService.approveOfficer(id));
    }

    @PutMapping("/officers/reject/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> rejectOfficer(@PathVariable Long id, Authentication auth) {
        return ResponseEntity.ok(officerService.rejectOfficer(id));
    }

    @GetMapping("/complaints")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllComplaints(Authentication auth) {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @GetMapping("/complaints/by-department")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getComplaintsByDepartment(@RequestParam String department, Authentication auth) {
        return ResponseEntity.ok(complaintService.getComplaintsByDepartment(department));
    }

    @GetMapping("/complaints/count")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> countByDept(@RequestParam String department, Authentication auth) {
        long count = complaintService.countByDepartment(department);
        return ResponseEntity.ok(Map.of("department", department, "total", count));
    }
}
