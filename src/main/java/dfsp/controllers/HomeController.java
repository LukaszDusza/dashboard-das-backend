package dfsp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
* Klasa widoku
* */

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }
}
