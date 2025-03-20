package fr.hovedopgave.demo.controller;

import fr.hovedopgave.demo.dto.LoginDTO;
import fr.hovedopgave.demo.dto.RegisterDTO;
import fr.hovedopgave.demo.model.Role;
import fr.hovedopgave.demo.model.UserEntity;
import fr.hovedopgave.demo.repository.RoleRepository;
import fr.hovedopgave.demo.repository.UserRepository;
import fr.hovedopgave.demo.security.AuthenticationHelper;
import fr.hovedopgave.demo.security.JWTGenerator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

import javax.validation.Valid;



@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationHelper authHelper;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder, 
                          JWTGenerator jwtGenerator,
                          AuthenticationHelper authHelper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.authHelper = authHelper;
    }

    @PostMapping("login")
    public String login(@Valid @ModelAttribute LoginDTO loginDto, BindingResult result, Model model, HttpServletResponse response) {
        model.addAttribute("isLoggedIn", authHelper.isLoggedIn());
        if (result.hasErrors()) {
            model.addAttribute("loginDto", loginDto); // Add loginDto back to model to avoid the error
            return "view/common/login"; 
        }
        
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()
                )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate the JWT token
            String token = jwtGenerator.generateToken(authentication);

            Cookie jwtCookie = new Cookie("jwtToken", token);
            jwtCookie.setSecure(true); // Only send over HTTPS
            jwtCookie.setHttpOnly(true);  
            jwtCookie.setMaxAge(600 * 600); 
            jwtCookie.setPath("/");       
            response.addCookie(jwtCookie);

            return "redirect:/index";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid username or password. Please try again.");
            model.addAttribute("loginDto", loginDto); 
            return "view/common/login"; 
        }
    }

@GetMapping("/info/login")
public String showLoginForm(Model model) {
    model.addAttribute("isLoggedIn", authHelper.isLoggedIn());
    if (!model.containsAttribute("loginDto")) {
        model.addAttribute("loginDto", new LoginDTO()); // Initialize loginDto
    }
    return "view/common/login";     
}

    

    @PostMapping("register")
    public String register(@Valid @ModelAttribute RegisterDTO registerDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("isLoggedIn", authHelper.isLoggedIn());
        
        if (result.hasErrors()) {
            return "view/common/register"; 
        }

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            model.addAttribute("error", "Username is already taken!");
            return "view/common/register";
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER")
            .orElseThrow(() -> new RuntimeException("Error: Role USER is not found."));
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);

        redirectAttributes.addFlashAttribute("message", "User registered successfully!");
        return "redirect:/info/login"; 
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        // Remove the JWT cookie
        Cookie jwtCookie = new Cookie("jwtToken", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setMaxAge(0);  // Expire the cookie immediately
        jwtCookie.setPath("/");  // Ensure it applies to the whole domain
        response.addCookie(jwtCookie);

        // Redirect to login or home page
        return "redirect:/info/login";
    }

}

