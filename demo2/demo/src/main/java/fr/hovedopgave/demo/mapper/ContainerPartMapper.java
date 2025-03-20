package fr.hovedopgave.demo.mapper;

import org.mapstruct.Mapper;
import fr.hovedopgave.demo.dto.ContainerPartDTO;
import fr.hovedopgave.demo.model.ContainerPart;

@Mapper(componentModel = "spring")
public interface ContainerPartMapper {
    ContainerPartDTO toDTO(ContainerPart containerPart);
    ContainerPart toEntity(ContainerPartDTO containerPartDTO);
}
