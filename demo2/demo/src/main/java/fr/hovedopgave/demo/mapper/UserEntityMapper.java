package fr.hovedopgave.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import fr.hovedopgave.demo.dto.UserEntityDTO;
import fr.hovedopgave.demo.model.UserEntity;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserEntityMapper {
    @Mapping(target = "roles", source = "roles") // Use RoleMapper to map roles
    UserEntityDTO toDTO(UserEntity userEntity);

    @Mapping(target = "roles", source = "roles") // Use RoleMapper to map roles
    UserEntity toEntity(UserEntityDTO userEntityDTO);
}
