package fr.hovedopgave.demo.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "container_part")
public class ContainerPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "containerPart_id")
    private int containerPartId;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "containerParts", fetch = FetchType.LAZY)
    private List<Container> containers;

}