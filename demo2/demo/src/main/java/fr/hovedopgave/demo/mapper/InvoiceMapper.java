package fr.hovedopgave.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import fr.hovedopgave.demo.dto.InvoiceDTO;
import fr.hovedopgave.demo.model.Invoice;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    @Mapping(target = "reportId", source = "report.reportId") 
    InvoiceDTO toDTO(Invoice invoice);

    @Mapping(target = "report.reportId", source = "reportId") 
    Invoice toEntity(InvoiceDTO invoiceDTO);
}
