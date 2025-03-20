package fr.hovedopgave.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.hovedopgave.demo.dto.ContainerDTO;
import fr.hovedopgave.demo.dto.ContainerPartDTO;
import fr.hovedopgave.demo.dto.ReportDTO;
import fr.hovedopgave.demo.model.Container;
import fr.hovedopgave.demo.model.ContainerPart;
import fr.hovedopgave.demo.model.Report;
import fr.hovedopgave.demo.repository.ReportRepository;
import fr.hovedopgave.demo.security.AuthenticationHelper;
import fr.hovedopgave.demo.service.impl.ContainerServiceImpl;
import fr.hovedopgave.demo.service.impl.ReportServiceImpl;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;
import java.nio.file.*;

@Controller
@RequestMapping("/inspect")
public class InspectorController {

    private final AuthenticationHelper authHelper;
    private final ContainerServiceImpl containerService;
    private final ReportServiceImpl reportService;
    private static final String UPLOAD_DIR = "uploads/";
    
    public InspectorController(AuthenticationHelper authHelper, ContainerServiceImpl containerService, ReportServiceImpl reportService) {
        this.authHelper = authHelper;
        this.containerService = containerService;
        this.reportService = reportService;
    }

    @GetMapping("/overview")
    public String containerOverview(Model model) {
        model.addAttribute("isLoggedIn", authHelper.isLoggedIn());
        List<ContainerDTO> containers = containerService.getContainersByStatus("Ready_for_check");
        model.addAttribute("containers", containers);
        return "view/common/overview";  
    }

    

    @GetMapping("/container/{id}/{partIndex}/{reportId}")
    public String checkContainerPart(HttpServletRequest request, @PathVariable int id, 
                                    @PathVariable int reportId, @PathVariable int partIndex, 
                                    Model model) {
        model.addAttribute("isLoggedIn", authHelper.isLoggedIn());
        
        ContainerDTO container = containerService.getContainersById(id);

        List<ContainerPartDTO> parts = container.getContainerParts();

        // If all parts are checked, redirect to overview
        if (partIndex >= parts.size()) {
            return "redirect:/inspect/overview";
        }
        if (reportId == 0) {
            reportId = reportService.newReport(partIndex, request, id);
        }
        
        model.addAttribute("reportId", reportId);
        model.addAttribute("id", id);
        model.addAttribute("currentPart", parts.get(partIndex).getName());
        model.addAttribute("partIndex", partIndex);
        model.addAttribute("totalParts", parts.size());
        

        return "view/inspector/inspectContainer"; 
    }

    @PostMapping("/{containerId}/{partIndex}/{reportId}")
    public String submitContainerPart(@PathVariable int containerId, @PathVariable int partIndex,
                                      @PathVariable int reportId, @RequestParam("note") String note, @RequestParam("condition") String condition, 
                                      @RequestParam("photo") MultipartFile photo) {


        reportService.saveDamagedItem(reportId, note, condition, containerId, partIndex);
        /*ContainerDTO container = containerService.getContainersById(containerId);                       
    
        try {
            // Save the uploaded file
            if (!photo.isEmpty()) {
                String filename = "container_" + containerId + "_part" + partIndex + "_" + photo.getOriginalFilename();
                Path filepath = Paths.get(UPLOAD_DIR, filename);
                Files.createDirectories(filepath.getParent());
                Files.write(filepath, photo.getBytes());
                System.out.println("Photo saved: " + filepath);
            }

            System.out.println("Note for container " + containerId + " part " + partIndex + ": " + note);

        } catch (IOException | java.io.IOException e) {
            e.printStackTrace();
        }*/

        // Redirect to the next part
        return "redirect:/inspect/container/" + containerId + "/" + partIndex + "/" + reportId;
    }
}
