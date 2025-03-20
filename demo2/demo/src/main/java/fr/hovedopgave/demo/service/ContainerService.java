package fr.hovedopgave.demo.service;

import java.util.List;

import fr.hovedopgave.demo.dto.ContainerDTO;

public interface ContainerService {
    List<ContainerDTO> getContainersByStatus(String status);
    ContainerDTO getContainersById(int id);
}
