package fr.hovedopgave.demo.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.hovedopgave.demo.dto.RegisterDTO;
import fr.hovedopgave.demo.model.Role;
import fr.hovedopgave.demo.model.UserEntity;
import fr.hovedopgave.demo.repository.RoleRepository;
import fr.hovedopgave.demo.repository.UserRepository;
import fr.hovedopgave.demo.security.AuthenticationHelper;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    private final AuthenticationHelper authHelper;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    
    public UserController(AuthenticationHelper authHelper, RoleRepository roleRepository, UserRepository userRepository) {
        this.authHelper = authHelper;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/roles")
    public String roles(Model model) {
        model.addAttribute("isLoggedIn", authHelper.isLoggedIn());
        
        // Fetch all roles from the database
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);

        return "view/user/applyRole";
    }


    @PostMapping("/submit")
    public String roleSubmit(Model model, HttpServletRequest request, @RequestParam("role") String roleName) {
        model.addAttribute("isLoggedIn", authHelper.isLoggedIn());
        model.addAttribute("role", authHelper.isLoggedIn());
        
        Role role = roleRepository.findByName(roleName)
            .orElseThrow(() -> new RuntimeException("Error: Role not found."));
        
        UserEntity user = authHelper.getUser(request);
        user.addRole(role);

        userRepository.save(user);

        return "redirect:/index";
    }
}