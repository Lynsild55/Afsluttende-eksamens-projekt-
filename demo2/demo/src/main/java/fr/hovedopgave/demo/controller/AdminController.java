package fr.hovedopgave.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.hovedopgave.demo.dto.ContainerDTO;
import fr.hovedopgave.demo.model.Report;
import fr.hovedopgave.demo.repository.ReportRepository;
import fr.hovedopgave.demo.security.AuthenticationHelper;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AuthenticationHelper authHelper;
    private final ReportRepository reportRepository;

    public AdminController(AuthenticationHelper authHelper, ReportRepository reportRepository) {
        this.authHelper = authHelper;
        this.reportRepository = reportRepository;
    }

    @GetMapping("/confirm")
    public String confirmReports(Model model) {
        model.addAttribute("isLoggedIn", authHelper.isLoggedIn());
        List<Report> reports = reportRepository.findByStatusIgnoreCase("UNPAID");
        model.addAttribute("reports", reports);
        return "view/admin/confirmReports";
        
    }

    @GetMapping("/details/{id}")
    public String reportDetails(Model model, @PathVariable int id) {
        model.addAttribute("isLoggedIn", authHelper.isLoggedIn());
        Report report = reportRepository.findById(id).orElse(null);
        model.addAttribute("report", report);
        return "view/admin/reportDetails";
        
    }

    
    
    
}
