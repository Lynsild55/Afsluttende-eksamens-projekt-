package fr.hovedopgave.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.hovedopgave.demo.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}