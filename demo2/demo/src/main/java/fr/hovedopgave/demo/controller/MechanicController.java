package fr.hovedopgave.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.hovedopgave.demo.security.AuthenticationHelper;

@Controller
@RequestMapping("/mechanic")
public class MechanicController {

    private final AuthenticationHelper authHelper;
    
    public MechanicController(AuthenticationHelper authHelper) {
        this.authHelper = authHelper;
    }
}
