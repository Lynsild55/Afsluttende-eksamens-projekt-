package fr.hovedopgave.demo.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private int reportId; 
    private LocalDateTime date; 
    private String status; 
    
    private int containerId; 
    private int inspectorId; 

    private List<Integer> invoiceIds; 
    private List<Integer> damagedItemIds; 
}
