package mk.ukim.finki.dashw.controller;

import mk.ukim.finki.dashw.service.HospitalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final HospitalService hospitalService;

    public HomeController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/home")
    public String getHomePage(Model model){
        return "home.html";
    }

}
