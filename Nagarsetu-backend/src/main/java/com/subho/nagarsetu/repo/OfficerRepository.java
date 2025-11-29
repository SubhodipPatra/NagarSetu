package com.subho.nagarsetu.repo;

import com.subho.nagarsetu.model.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OfficerRepository extends JpaRepository<Officer, Long> {
    List<Officer> findByDepartmentAndApprovedTrue(String department);
    Optional<Officer> findByEmail(String email);
}
