package fr.hovedopgave.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;
    private String name;
    private String address;

    @OneToMany(mappedBy = "company")
    private List<Container> containers;

    public String getName() {
        return name;
    }
    
}