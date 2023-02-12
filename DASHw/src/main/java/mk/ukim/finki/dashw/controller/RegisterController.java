package mk.ukim.finki.dashw.controller;

import mk.ukim.finki.dashw.model.enumerations.Role;
import mk.ukim.finki.dashw.model.exceptions.InvalidUserCredentialException;
import mk.ukim.finki.dashw.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.dashw.model.exceptions.UserRoleNotFoundException;
import mk.ukim.finki.dashw.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.dashw.service.AuthService;
import mk.ukim.finki.dashw.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role userRole){
        try{
            this.userService.register(username, password, repeatPassword, name, surname, userRole);
            return "redirect:/login";
        }
        catch(PasswordsDoNotMatchException | UsernameAlreadyExistsException | InvalidUserCredentialException |
                UserRoleNotFoundException exception){
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
