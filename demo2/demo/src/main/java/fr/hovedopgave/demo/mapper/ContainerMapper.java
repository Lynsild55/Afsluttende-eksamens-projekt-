package fr.hovedopgave.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import fr.hovedopgave.demo.dto.ContainerDTO;
import fr.hovedopgave.demo.model.Container;

@Mapper(componentModel = "spring", uses = ContainerPartMapper.class)
public interface ContainerMapper {

    @Mapping(target = "companyName", expression = "java(container.getCompany() != null ? container.getCompany().getName() : \"Unknown\")")
    @Mapping(target = "containerParts", source = "containerParts") 
    ContainerDTO toDTO(Container container);

    Container toEntity(ContainerDTO containerDTO);
}
