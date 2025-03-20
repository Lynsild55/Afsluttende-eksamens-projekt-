package fr.hovedopgave.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.hovedopgave.demo.security.AuthenticationHelper;

@Controller
@RequestMapping("/owner")
public class ConOwnerController {

    private final AuthenticationHelper authHelper;
    
    public ConOwnerController(AuthenticationHelper authHelper) {
        this.authHelper = authHelper;
    }
}
