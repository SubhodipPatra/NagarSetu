package com.subho.nagarsetu.dto;

import com.subho.nagarsetu.model.ComplaintType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintResponseDTO {
    private Long complaintId;
    private String description;
    private ComplaintType type;
    private String userPincode;
    private String assignedOfficerName;
    private String department;
    private String officerPincode;
}
