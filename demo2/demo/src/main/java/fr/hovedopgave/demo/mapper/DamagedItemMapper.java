package fr.hovedopgave.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import fr.hovedopgave.demo.dto.DamagedItemDTO;
import fr.hovedopgave.demo.model.DamagedItem;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DamagedItemMapper {

    @Mapping(target = "reportId", source = "report.reportId") 
    @Mapping(target = "containerPartId", source = "containerPart.containerPartId") 
    DamagedItemDTO toDTO(DamagedItem damagedItem);

    @Mapping(target = "report.reportId", source = "reportId") 
    @Mapping(target = "containerPart.containerPartId", source = "containerPartId") 
    DamagedItem toEntity(DamagedItemDTO damagedItemDTO);
}
