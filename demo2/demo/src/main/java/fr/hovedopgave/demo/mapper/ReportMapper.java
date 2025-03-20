package fr.hovedopgave.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;  // Import the @Named annotation
import fr.hovedopgave.demo.dto.ReportDTO;
import fr.hovedopgave.demo.model.DamagedItem;
import fr.hovedopgave.demo.model.Invoice;
import fr.hovedopgave.demo.model.Report;

@Mapper(componentModel = "spring", uses = { UserEntityMapper.class, DamagedItemMapper.class, InvoiceMapper.class })
public interface ReportMapper {

    @Mappings({
        @Mapping(target = "containerId", source = "container.containerId"),
        @Mapping(target = "inspectorId", source = "inspector.id"),
        @Mapping(target = "invoiceIds", source = "invoices", qualifiedByName = "mapInvoicesToIds"),
        @Mapping(target = "damagedItemIds", source = "damagedItems", qualifiedByName = "mapDamagedItemsToIds")
    })
    ReportDTO toDTO(Report report);

    @Mappings({
        @Mapping(target = "container.containerId", source = "containerId"),
        @Mapping(target = "inspector.id", source = "inspectorId"),
        @Mapping(target = "invoices", source = "invoiceIds", qualifiedByName = "mapIdsToInvoices"),  
        @Mapping(target = "damagedItems", source = "damagedItemIds", qualifiedByName = "mapIdsToDamagedItems")  
    })
    Report toEntity(ReportDTO reportDTO);

    
    @Named("mapInvoicesToIds")
    default List<Integer> mapInvoicesToIds(List<Invoice> invoices) {
        if (invoices == null) {
            return null;
        }
        return invoices.stream()
                .map(Invoice::getInvoiceId)  
                .collect(Collectors.toList());
    }

    

    // Custom method to map List<Integer> (invoiceIds) to List<Invoice> (with @Named annotation)
    @Named("mapIdsToInvoices")
    default List<Invoice> mapIdsToInvoices(List<Integer> invoiceIds) {
        if (invoiceIds == null) {
            return null;
        }
        return invoiceIds.stream()
                .map(id -> new Invoice())  
                .collect(Collectors.toList());
    }

    // Custom method to map List<DamagedItem> to List<Integer> (with @Named annotation)
    @Named("mapDamagedItemsToIds")
    default List<Integer> mapDamagedItemsToIds(List<DamagedItem> damagedItems) {
        if (damagedItems == null) {
            return null;
        }
        return damagedItems.stream()
                .map(DamagedItem::getId)  
                .collect(Collectors.toList());
    }

    // Custom method to map List<Integer> (damagedItemIds) to List<DamagedItem> (with @Named annotation)
    @Named("mapIdsToDamagedItems")
    default List<DamagedItem> mapIdsToDamagedItems(List<Integer> damagedItemIds) {
        if (damagedItemIds == null) {
            return null;
        }
        return damagedItemIds.stream()
                .map(id -> new DamagedItem())  
                .collect(Collectors.toList());
    }
}
