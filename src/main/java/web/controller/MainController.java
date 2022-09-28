package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {

    UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String getCurrentUserInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userService.getUserByLogin(user.getLogin()));
        return "show";
    }

    @GetMapping("/admin/users")
    public String getAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userService.getUserByLogin(user.getLogin()));
        return "index";
    }
}
