package mk.ukim.finki.dashw.controller;

import mk.ukim.finki.dashw.model.Hospital;
import mk.ukim.finki.dashw.model.Municipality;
import mk.ukim.finki.dashw.model.Pharmacy;
import mk.ukim.finki.dashw.model.exceptions.ProblemSolvingError;
import mk.ukim.finki.dashw.service.PharmacyService;
import mk.ukim.finki.dashw.service.impl.MunicipalityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class PharmacyController {
    private final PharmacyService pharmacyService;
    private final MunicipalityService municipalityService;

    public PharmacyController(PharmacyService pharmacyService, MunicipalityService municipalityService) {
        this.pharmacyService = pharmacyService;
        this.municipalityService = municipalityService;
    }

    @GetMapping("/pharmacies")
    public String getHospitals(Model model,
                               @RequestParam(required = false) String municipality,
                               @RequestParam(required = false) String destination,
                               @RequestParam(required = false) String error
    ) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Pharmacy> pharmacies;
        if ((municipality == null || municipality.equals("")) && destination == null) {
            pharmacies = pharmacyService.findAll();
            List<Municipality> municipalities = municipalityService.findAll();
            model.addAttribute("pharmacies", pharmacies);
            model.addAttribute("municipalities", municipalities);
        } else {
            pharmacies = pharmacyService.findAllByMunicipality(municipality);
            List<Municipality> municipalities = municipalityService.findAll();
            model.addAttribute("pharmacies", pharmacies);
            model.addAttribute("municipalities", municipalities);
        }
        if (destination != null) {
            try {
                this.pharmacyService.incrementSearchCount(destination);
                pharmacies = this.pharmacyService.findAll();
                model.addAttribute("pharmacies", pharmacies);
            } catch (ProblemSolvingError e) {
                return "redirect:/pharmacies?error=" + e.getMessage();
            }
        }
        return "pharmacies";
    }


    @PostMapping("/addPharmacies")
    public String insertPharmacies(@RequestParam String pharmaciesUrl) throws IOException {


        this.pharmacyService.addPharmacy(pharmaciesUrl);

        return "redirect:/home";
    }

    @GetMapping("/pharmacyStatistics")
    public String hospitalStatistics(Model model) {
        List<Pharmacy> pharmacies = this.pharmacyService.findMostSearchedHospitals();
        model.addAttribute("pharmacies", pharmacies);

        return "pharmacyStatistics";
    }
}
