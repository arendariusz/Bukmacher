package com.example.bukmacher.admin;

import com.example.bukmacher.user.User;
import com.example.bukmacher.user.UserRole;
import com.example.bukmacher.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPanel(Model model) {

        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "admin";
    }

    @GetMapping("/admin/configureRoles/{id}")
    public String configureUserRoles(@PathVariable Long id, Model model) {
        Set<UserRole> userRoles = userService.getUserRoles(id);
        User user = userService.findUserById(id);
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("user", user);
        return "roleConfig";
    }

    @GetMapping("admin/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable Long id) {

        userService.deleteAdminRoleByUserId(id);

        return "admin";
    }

    @GetMapping("admin/addAdminRole/{id}")
    public String addAdmin(@PathVariable Long id) {

        userService.addAdminRoleByUserId(id);

        return "admin";
    }
}
