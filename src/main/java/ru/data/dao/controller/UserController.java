package ru.data.dao.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.data.dao.model.User;
import ru.data.dao.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/auth_page")
    public String authenticatedPage() {
        return "authenticated";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/user_info")
    public String daoTestPage(Principal principal) {
        User user = userService
                .findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Unable to find user by name: " + principal.getName()));
        return "Authenticated user info: " + user.getName() + " : " + user.getEmail();
    }

    @GetMapping(value = "/reg")
    public String home() {
        return "reg";
    }

    @PostMapping("/reg")
    public String add(@RequestParam String name, @RequestParam String password, @RequestParam String email) {
        User user = new User(name, password, email);
        Optional<User> userFromDb = userService.findByUsername(user.getName());
        if (userFromDb.isPresent()) {
            return "reg";
        }
        userService.save(user);
        return "reg";
    }


}
