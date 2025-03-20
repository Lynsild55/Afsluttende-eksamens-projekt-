package fr.hovedopgave.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.hovedopgave.demo.model.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findByStatusIgnoreCase(String status);
}