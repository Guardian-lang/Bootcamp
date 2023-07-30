package by.ahmed.controller;

import by.ahmed.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public String users(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "users";
    }

    @PostMapping("/find")
    public String findUser(@RequestParam("email") String email,
                           Model model) {
        if (userService.findByEmail(email).isPresent()) {
            model.addAttribute("user", userService.findByEmail(email).orElseThrow());
        }
        else {
            model.addAttribute("error", "User not found");
        }
        return "users";
    }
}
