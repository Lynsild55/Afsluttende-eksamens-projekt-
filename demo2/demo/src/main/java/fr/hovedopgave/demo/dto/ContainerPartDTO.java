package fr.hovedopgave.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  
@NoArgsConstructor  
@AllArgsConstructor 
public class ContainerPartDTO {
    private int containerPartId;
    private String name;
    private String description;

}
