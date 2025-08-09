package com.subho.nagarsetu.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String userPincode;
    private String userEmail;

    @Enumerated(EnumType.STRING)
    private ComplaintType type;

    @ManyToOne
    private Officer officer;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status = ComplaintStatus.PENDING;

    private String remarks;
}
