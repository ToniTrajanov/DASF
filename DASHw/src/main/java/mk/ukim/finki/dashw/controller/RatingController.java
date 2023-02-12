package mk.ukim.finki.dashw.controller;

import mk.ukim.finki.dashw.model.User;
import mk.ukim.finki.dashw.service.RatingService;
import mk.ukim.finki.dashw.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/rating")
public class RatingController {
    private final RatingService ratingService;
    private final UserService userService;

    public RatingController(RatingService ratingService, UserService userService) {
        this.ratingService = ratingService;
        this.userService = userService;
    }

    @GetMapping
    public String addRating(Model model){
        double rating = ratingService.calculate();
        model.addAttribute("rating", rating);
        return "addRating";
    }

    @PostMapping
    public String addRating(@RequestParam String comment,
                            @RequestParam String rating,
                            HttpServletRequest req){
        String username = req.getRemoteUser();
        User user = userService.findByUserName(username);
        ratingService.create(user, Integer.parseInt(rating), comment);
        return "redirect:/home";
    }
}
