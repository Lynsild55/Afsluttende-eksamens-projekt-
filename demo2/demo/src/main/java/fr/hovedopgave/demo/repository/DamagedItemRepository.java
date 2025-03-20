package fr.hovedopgave.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.hovedopgave.demo.model.DamagedItem;

public interface DamagedItemRepository extends JpaRepository<DamagedItem, Integer> {
}
