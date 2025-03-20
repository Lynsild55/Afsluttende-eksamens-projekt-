package fr.hovedopgave.demo;

import fr.hovedopgave.demo.dto.*;
import fr.hovedopgave.demo.mapper.*;
import fr.hovedopgave.demo.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

@SpringBootTest
class MapperTests {

    @Autowired
    private ContainerMapper containerMapper;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private ContainerPartMapper containerPartMapper;

    @Autowired
    private DamagedItemMapper damagedItemMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserEntityMapper userEntityMapper;

    // Test context load
    @Test
    void contextLoads() {
        assertThat(containerMapper).isNotNull();
        assertThat(invoiceMapper).isNotNull();
        assertThat(containerPartMapper).isNotNull();
        assertThat(damagedItemMapper).isNotNull();
        assertThat(reportMapper).isNotNull();
        assertThat(roleMapper).isNotNull();
        assertThat(userEntityMapper).isNotNull();
    }

    // Test ContainerMapper
    @Test
    void testContainerMapper() {
        // Arrange
        Company company = new Company();
        company.setCompanyId(1);
        company.setName("Company A");
        company.setAddress("Address");

        Container container = new Container();
        container.setContainerId(1);
        container.setSerialTag("Serial123");
        container.setLength(10);
        container.setType("TypeA");
        container.setWeight(100.5f);
        container.setStatus("Active");
        container.setCompany(company);

        // Act
        ContainerDTO containerDTO = containerMapper.toDTO(container);

        // Assert
        assertThat(containerDTO).isNotNull();
        assertThat(containerDTO.getContainerId()).isEqualTo(container.getContainerId());
        assertThat(containerDTO.getSerialTag()).isEqualTo(container.getSerialTag());
        assertThat(containerDTO.getLength()).isEqualTo(container.getLength());
        assertThat(containerDTO.getType()).isEqualTo(container.getType());
        assertThat(containerDTO.getWeight()).isEqualTo(container.getWeight());
        assertThat(containerDTO.getStatus()).isEqualTo(container.getStatus());
        assertThat(containerDTO.getCompanyName()).isEqualTo("Company A");
    }

    // Test InvoiceMapper
    @Test
    void testInvoiceMapper() {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setResult("Paid");
        invoice.setRemarks("No remarks");

        // Act
        InvoiceDTO invoiceDTO = invoiceMapper.toDTO(invoice);

        // Assert
        assertThat(invoiceDTO).isNotNull();
        assertThat(invoiceDTO.getInvoiceId()).isEqualTo(invoice.getInvoiceId());
        assertThat(invoiceDTO.getResult()).isEqualTo(invoice.getResult());
        assertThat(invoiceDTO.getRemarks()).isEqualTo(invoice.getRemarks());
        assertThat(invoiceDTO.getReportId()).isEqualTo(0);
    }

    // Test ContainerPartMapper
    @Test
    void testContainerPartMapper() {
        // Arrange
        ContainerPart containerPart = new ContainerPart();
        containerPart.setContainerPartId(1);
        containerPart.setName("PartA");
        containerPart.setDescription("Description");

        // Act
        ContainerPartDTO containerPartDTO = containerPartMapper.toDTO(containerPart);

        // Assert
        assertThat(containerPartDTO).isNotNull();
        assertThat(containerPartDTO.getContainerPartId()).isEqualTo(containerPart.getContainerPartId());
        assertThat(containerPartDTO.getName()).isEqualTo(containerPart.getName());
        assertThat(containerPartDTO.getDescription()).isEqualTo(containerPart.getDescription());
    }

    // Test ReportMapper (including complex list mapping)
    @Test
    void testReportMapper() {
        // Arrange
        Container container = new Container();
        container.setContainerId(1);
        container.setSerialTag("Serial123");
        container.setLength(10);
        container.setType("TypeA");
        container.setWeight(100.5f);
        container.setStatus("Active");

        Report report = new Report();
        report.setReportId(1);
        report.setStatus("Active");
        report.setContainer(container);
        report.setInvoices(Arrays.asList(new Invoice(1, "Paid", "No remarks", null)));
        report.setDamagedItems(Arrays.asList(new DamagedItem(1, "Damaged", "Broken", report, null)));

        // Act
        ReportDTO reportDTO = reportMapper.toDTO(report);

        // Assert
        assertThat(reportDTO).isNotNull();
        assertThat(reportDTO.getReportId()).isEqualTo(report.getReportId());
        assertThat(reportDTO.getStatus()).isEqualTo(report.getStatus());
        assertThat(reportDTO.getContainerId()).isEqualTo(report.getContainer().getContainerId());
        assertThat(reportDTO.getInvoiceIds()).containsExactlyInAnyOrder(1);
        assertThat(reportDTO.getDamagedItemIds()).containsExactlyInAnyOrder(1);
    }

    // Test RoleMapper
    @Test
    void testRoleMapper() {
        // Arrange
        Role role = new Role();
        role.setId(1);
        role.setName("Admin");

        // Act
        RoleDTO roleDTO = roleMapper.toDTO(role);

        // Assert
        assertThat(roleDTO).isNotNull();
        assertThat(roleDTO.getId()).isEqualTo(role.getId());
        assertThat(roleDTO.getName()).isEqualTo(role.getName());
    }

    // Test UserEntityMapper (with roles mapping)
    @Test
    void testUserEntityMapper() {
        // Arrange
        Role role = new Role();
        role.setId(1);
        role.setName("Admin");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setUsername("john_doe");
        userEntity.setPassword("password");
        userEntity.setRoles(Arrays.asList(role));

        // Act
        UserEntityDTO userEntityDTO = userEntityMapper.toDTO(userEntity);

        // Assert
        assertThat(userEntityDTO).isNotNull();
        assertThat(userEntityDTO.getId()).isEqualTo(userEntity.getId());
        assertThat(userEntityDTO.getUsername()).isEqualTo(userEntity.getUsername());
        assertThat(userEntityDTO.getRoles()).isNotEmpty();
        assertThat(userEntityDTO.getRoles()).contains(roleMapper.toDTO(role));
    }

    /* Test Null Handling for DamagedItemMapper
    @Test
    void testDamagedItemMapperWithNullValues() {
        // Arrange
        DamagedItem damagedItem = new DamagedItem();
        damagedItem.setId(1);
        damagedItem.setCondition(null);
        damagedItem.setNote(null);
        damagedItem.setItem(null);
        damagedItem.setReport(null);

        // Act
        DamagedItemDTO damagedItemDTO = damagedItemMapper.toDTO(damagedItem);

        // Assert
        assertThat(damagedItemDTO).isNotNull();
        assertThat(damagedItemDTO.getId()).isEqualTo(damagedItem.getId());  // Expect null if no ID
        assertThat(damagedItemDTO.getCondition()).isNull();
        assertThat(damagedItemDTO.getNote()).isNull();
        assertThat(damagedItemDTO.getReportId()).isEqualTo(0);  // Expect null if no report
    }*/

    // Test list of items mapping (InvoiceMapper to ReportDTO)
    @Test
    void testReportMapperWithMultipleInvoices() {
        // Arrange
        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceId(1);
        invoice1.setResult("Paid");
        invoice1.setRemarks("Remarks 1");

        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceId(2);
        invoice2.setResult("Unpaid");
        invoice2.setRemarks("Remarks 2");

        Report report = new Report();
        report.setReportId(1);
        report.setStatus("Active");
        report.setInvoices(Arrays.asList(invoice1, invoice2));

        // Act
        ReportDTO reportDTO = reportMapper.toDTO(report);

        // Assert
        assertThat(reportDTO.getInvoiceIds()).containsExactlyInAnyOrder(1, 2);
    }
}
