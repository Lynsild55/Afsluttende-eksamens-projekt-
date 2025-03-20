package fr.hovedopgave.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.hovedopgave.demo.model.Container;

public interface ContainerRepository extends JpaRepository<Container, Integer> {
    List<Container> findByStatusIgnoreCase(String status);
    
    @Query("SELECT c FROM Container c LEFT JOIN FETCH c.containerParts WHERE c.containerId = :id")
    Optional<Container> findByIdWithParts(@Param("id") int id);
    
}

