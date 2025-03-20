package fr.hovedopgave.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DamagedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 

    private String condition; 

    private String note;

    
    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    // Link to the ContainerPart
    @ManyToOne
    @JoinColumn(name = "container_part_id") 
    private ContainerPart containerPart;
    
}
