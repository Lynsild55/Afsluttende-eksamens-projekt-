package fr.hovedopgave.demo.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DamagedItemDTO {
    private int id;
    private String condition;
    private String note;
    private Integer reportId; 
    private Integer containerPartId; 
}
