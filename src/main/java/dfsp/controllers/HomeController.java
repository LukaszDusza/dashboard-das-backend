package dfsp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Klasa widoku */

@Controller
public class HomeController {

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/")
    public String homePage() {
        return "index";
    }
}
