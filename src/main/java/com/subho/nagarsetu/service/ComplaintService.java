package com.subho.nagarsetu.service;

import com.subho.nagarsetu.model.Complaint;
import com.subho.nagarsetu.model.ComplaintStatus;
import com.subho.nagarsetu.model.ComplaintType;
import com.subho.nagarsetu.model.Officer;
import com.subho.nagarsetu.repo.ComplaintRepository;
import com.subho.nagarsetu.repo.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepo;

    @Autowired
    private OfficerRepository officerRepo;

    public Complaint submitComplaint(Complaint complaint) {
        Officer assigned = assignOfficer(complaint.getType(), complaint.getUserPincode());
        if (assigned == null) {
            throw new RuntimeException("No available officer for department: " + mapTypeToDepartment(complaint.getType()));
        }
        complaint.setOfficer(assigned);
        complaint.setStatus(ComplaintStatus.PENDING); // Default status
        return complaintRepo.save(complaint);
    }

    private Officer assignOfficer(ComplaintType type, String pincode) {
        String department = mapTypeToDepartment(type);
        List<Officer> officers = officerRepo.findByDepartmentAndApprovedTrue(department);

        for (Officer officer : officers) {
            if (officer.getLocation().equals(pincode)) {
                return officer;
            }
        }

        return officers.isEmpty() ? null : officers.get(0);
    }

    private String mapTypeToDepartment(ComplaintType type) {
        return switch (type) {
            case DEVELOPMENT -> "BDO";
            case ISSUE -> "Gram Panchayat";
            default -> type.toString(); // ELECTRICITY, WATER, etc.
        };
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepo.findAll();
    }

    public List<Complaint> getComplaintsByUserEmail(String email) {
        return complaintRepo.findByUserEmail(email);
    }

    public Complaint getComplaintById(Long id) {
        return complaintRepo.findById(id).orElse(null);
    }

    public List<Complaint> getComplaintsByDepartment(String department) {
        return complaintRepo.findByOfficerDepartment(department);
    }

    public long countByDepartment(String department) {
        return complaintRepo.findByOfficerDepartment(department).size();
    }

    public List<Complaint> getComplaintsByOfficerEmail(String email) {
        return complaintRepo.findByOfficerEmail(email);
    }

    public List<Complaint> getComplaintsByDeptAndPincode(String department, String pincode) {
        return complaintRepo.findByOfficerDepartmentAndOfficerLocation(department, pincode);
    }

    public Complaint updateComplaintStatusForOfficer(Long complaintId, ComplaintStatus status, String remarks, String officerEmail) {
        Complaint complaint = complaintRepo.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        if (!complaint.getOfficer().getEmail().equals(officerEmail)) {
            throw new RuntimeException("You are not authorized to update this complaint");
        }

        complaint.setStatus(status);
        complaint.setRemarks(remarks);
        return complaintRepo.save(complaint);
    }

    public boolean deleteComplaintById(Long id) {
        if (complaintRepo.existsById(id)) {
            complaintRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
