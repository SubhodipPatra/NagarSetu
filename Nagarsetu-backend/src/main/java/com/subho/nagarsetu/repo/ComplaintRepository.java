package com.subho.nagarsetu.repo;
import com.subho.nagarsetu.model.Complaint;
import com.subho.nagarsetu.model.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    void deleteById(Long id);
    boolean existsById(Long id);
    List<Complaint> findByUserEmail(String email);
    List<Complaint> findByOfficerEmail(String email);
    List<Complaint> findByOfficerDepartment(String department);
    List<Complaint> findByOfficerDepartmentAndOfficerLocation(String department, String location);
}