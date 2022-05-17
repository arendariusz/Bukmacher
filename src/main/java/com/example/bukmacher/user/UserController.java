package com.example.bukmacher.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "register.html";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.registerUser(user);
        return "redirect:/registerSucces";
    }

    @GetMapping("/registerSucces")
    public String success() {

        return "registerSucces";
    }

    @GetMapping("/userPanel")
    public String userPanel(Model model, Principal principal) {
        Optional<User> user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user.get());
        return "userPanel";
    }

    @PostMapping("/userPanel")
    public String userUpdate(Principal principal, User user) {
        userService.updateUserByUsername(principal.getName(), user);
        return "redirect:/";
    }
}
