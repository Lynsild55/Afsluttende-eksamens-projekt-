package fr.hovedopgave.demo.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {
    private int invoiceId; 
    private String result; 
    private String remarks; 
    private int reportId; 
}
