package fr.hovedopgave.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int containerId;
    private String serialTag;
    private int length;
    private String type;
    private float weight;
    private String status;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "container_containerPart", 
        joinColumns = @JoinColumn(name = "container_id"), 
        inverseJoinColumns = @JoinColumn(name = "containerPart_id")
    )
    private List<ContainerPart> containerParts;

    @OneToMany(mappedBy = "container")
    private List<Report> reports;
}
