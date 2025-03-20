package fr.hovedopgave.demo.mapper;

import org.mapstruct.Mapper;
import fr.hovedopgave.demo.dto.RoleDTO;
import fr.hovedopgave.demo.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDTO(Role role);
    Role toEntity(RoleDTO roleDTO);
}
