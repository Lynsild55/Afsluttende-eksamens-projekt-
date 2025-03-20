package fr.hovedopgave.demo.service.impl;

import org.springframework.stereotype.Service;

import fr.hovedopgave.demo.dto.DamagedItemDTO;
import fr.hovedopgave.demo.mapper.DamagedItemMapper;
import fr.hovedopgave.demo.repository.DamagedItemRepository;

@Service
public class DamagedItemServiceImpl {
    private DamagedItemRepository damagedItemRepository;
    private DamagedItemMapper damagedItemMapper;
    
    public DamagedItemServiceImpl(DamagedItemRepository damagedItemRepository, DamagedItemMapper damagedItemMapper) {
        this.damagedItemRepository = damagedItemRepository;
        this.damagedItemMapper = damagedItemMapper;
    }

    public void saveDamagedItem(DamagedItemDTO damagedItemDTO) {
        damagedItemRepository.save(damagedItemMapper.toEntity(damagedItemDTO));
    }
}
