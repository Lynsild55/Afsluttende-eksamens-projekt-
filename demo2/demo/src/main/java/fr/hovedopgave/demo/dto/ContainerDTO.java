package fr.hovedopgave.demo.dto;

import java.util.List;
import fr.hovedopgave.demo.model.ContainerPart;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data  
@NoArgsConstructor  
@AllArgsConstructor 
public class ContainerDTO {
    private int containerId;
    private String serialTag;
    private int length;
    private String type;
    private String companyName;  
    private float weight;
    private List<ContainerPartDTO> containerParts; 
    private String status;
}
