package fr.hovedopgave.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;
    private LocalDateTime date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "container_id")
    private Container container;

    @ManyToOne
    @JoinColumn(name = "inspector_id")
    private UserEntity inspector;

    @OneToMany(mappedBy = "report")
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DamagedItem> damagedItems;
}
