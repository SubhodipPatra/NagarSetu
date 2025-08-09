package com.subho.nagarsetu.service;

import com.subho.nagarsetu.model.Officer;
import com.subho.nagarsetu.repo.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficerService {

    @Autowired
    private OfficerRepository officerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Officer registerOfficer(Officer officer) {
        officer.setPassword(passwordEncoder.encode(officer.getPassword()));
        officer.setRole("OFFICER");
        officer.setApproved(false);
        return officerRepo.save(officer);
    }

    public List<Officer> getAllOfficers() {
        return officerRepo.findAll();
    }

    public Optional<Officer> getOfficerById(Long id) {
        return officerRepo.findById(id);
    }

    public Officer approveOfficer(Long id) {
        return setApprovalStatus(id, true);
    }

    public Officer rejectOfficer(Long id) {
        return setApprovalStatus(id, false);
    }

    private Officer setApprovalStatus(Long id, boolean status) {
        Optional<Officer> optional = officerRepo.findById(id);
        if (optional.isPresent()) {
            Officer officer = optional.get();
            officer.setApproved(status);
            return officerRepo.save(officer);
        }
        return null;
    }

    public List<Officer> getApprovedByDepartment(String dept) {
        return officerRepo.findByDepartmentAndApprovedTrue(dept);
    }

    public List<Officer> getOfficersByApproval(boolean approved) {
        return officerRepo.findAll().stream()
                .filter(o -> o.isApproved() == approved)
                .toList();
    }

    public List<Officer> getOfficersByDepartment(String dept) {
        return officerRepo.findByDepartmentAndApprovedTrue(dept);
    }
    public Officer getOfficerByEmail(String email) {
        return officerRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Officer not found with email: " + email));
    }

}
