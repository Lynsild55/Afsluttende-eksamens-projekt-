package fr.hovedopgave.demo.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.hovedopgave.demo.dto.LoginDTO;
import fr.hovedopgave.demo.dto.RegisterDTO;
import fr.hovedopgave.demo.security.AuthenticationHelper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("")
public class HomeController {

    private final AuthenticationHelper authHelper;

    public HomeController(AuthenticationHelper authHelper) {
        this.authHelper = authHelper;
    }


    @GetMapping("/index")
    public String index(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        boolean isUser = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"));
        boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        boolean isModerator = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MODERATOR"));
        boolean isConOwner = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CON_OWNER"));
        boolean isMechanic = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MECHANIC"));
        boolean isInspector = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_INSPECTOR"));

        model.addAttribute("isUser", isUser);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isModerator", isModerator);
        model.addAttribute("isConOwner", isConOwner);
        model.addAttribute("isMechanic", isMechanic);
        model.addAttribute("isInspector", isInspector);

        model.addAttribute("isLoggedIn", authHelper.isLoggedIn());

        return "view/common/index";
    }

    @GetMapping("/info/login")
    public String showLoginForm(Model model) {
        if (!model.containsAttribute("loginDto")) {
            model.addAttribute("loginDto", new LoginDTO());
        }
        model.addAttribute("isLoggedIn", authHelper.isLoggedIn());
        return "view/common/login";     
    }


    @GetMapping("/info/register")
    public String registerView(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        model.addAttribute("isLoggedIn", authHelper.isLoggedIn());
        return "view/common/register";
    }

}
