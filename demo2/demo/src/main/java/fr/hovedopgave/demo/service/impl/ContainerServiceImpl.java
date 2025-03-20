package fr.hovedopgave.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.hovedopgave.demo.dto.ContainerDTO;
import fr.hovedopgave.demo.mapper.ContainerMapper;
import fr.hovedopgave.demo.model.Container;
import fr.hovedopgave.demo.repository.ContainerRepository;
import fr.hovedopgave.demo.service.ContainerService;

@Service
public class ContainerServiceImpl implements ContainerService {
    private final ContainerRepository containerRepository;
    private final ContainerMapper containerMapper;

    public ContainerServiceImpl(ContainerRepository containerRepository, ContainerMapper containerMapper) {
        this.containerRepository = containerRepository;
        this.containerMapper = containerMapper;
    }

    @Override
    public List<ContainerDTO> getContainersByStatus(String status) {
        List<Container> containers = containerRepository.findByStatusIgnoreCase(status);
        
        return containers.stream()
                         .map(containerMapper::toDTO)
                         .collect(Collectors.toList());
    }

    @Override
    public ContainerDTO getContainersById(int id) {
        Container container = containerRepository.findById(id).orElse(null);
        int i = container.getContainerParts().size(); 
        System.out.println("test2 " + container.getContainerId() + " and " + i);
        return containerMapper.toDTO(container);
    }
    
}
