package com.subho.nagarsetu.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
public class Officer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department; // BDO, Gram Panchayat, etc.
    private String location;   // Pincode
    private String email;
    private String password;
    private String role = "OFFICER";
    private boolean approved = false; // Default false

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
