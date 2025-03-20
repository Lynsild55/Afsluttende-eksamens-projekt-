package fr.hovedopgave.demo.service.impl;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import fr.hovedopgave.demo.dto.DamagedItemDTO;
import fr.hovedopgave.demo.dto.ReportDTO;
import fr.hovedopgave.demo.mapper.ReportMapper;
import fr.hovedopgave.demo.model.Report;
import fr.hovedopgave.demo.repository.ReportRepository;
import fr.hovedopgave.demo.security.AuthenticationHelper;
import fr.hovedopgave.demo.service.ReportService;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ReportServiceImpl implements ReportService{
    private ReportRepository reportRepository;
    private ReportMapper reportMapper;
    private AuthenticationHelper authHelper;
    private ContainerServiceImpl containerService;
    private DamagedItemServiceImpl damagedItemService;

    public ReportServiceImpl(ReportRepository reportRepository, ReportMapper reportMapper,
            AuthenticationHelper authHelper, ContainerServiceImpl containerService,
            DamagedItemServiceImpl damagedItemService) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
        this.authHelper = authHelper;
        this.containerService = containerService;
        this.damagedItemService = damagedItemService;
    }


    public void save(ReportDTO report) {
        reportRepository.save(reportMapper.toEntity(report));
    }
    

    public int newReport(int partIndex, HttpServletRequest request, int containerId) {
        if (partIndex == 0) {
            String status = "UNPAID";
            LocalDateTime dateTime = LocalDateTime.now();
            ReportDTO report = new ReportDTO();
            report.setStatus(status);
            report.setInspectorId(authHelper.getUser(request).getId());
            report.setDate(dateTime);
            report.setContainerId(containerId);
            Report savedReport = reportRepository.save(reportMapper.toEntity(report));
            return savedReport.getReportId();
        }
        return 0;
    }

    public void saveDamagedItem(int reportId, String note, String condition, int containerId, int partIndex) {
        if (condition != null) {
            DamagedItemDTO damagedItemDTO = new DamagedItemDTO();
            damagedItemDTO.setReportId(reportId);
            damagedItemDTO.setNote(note);
            damagedItemDTO.setCondition(condition);
            int containerPartId = containerService.getContainersById(containerId).getContainerParts().get(partIndex - 1).getContainerPartId();
            damagedItemDTO.setContainerPartId(containerPartId);
            damagedItemService.saveDamagedItem(damagedItemDTO);
        }
    }
}
