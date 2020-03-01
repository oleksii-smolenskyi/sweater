package com.smolenskyi.sweater.controller;

import com.smolenskyi.sweater.domain.User;
import com.smolenskyi.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(/*Map<String, Object> model*/) {
//        model.put("message", "");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {

        if(!userService.addUser(user)) {
            model.put("message", "Такий користувач вже існує.");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivate = userService.activateUser(code);
        if(isActivate) {
            model.addAttribute("message", "User successfully activated.");
        } else {
            model.addAttribute("message", "Activation code is not found.");
        }
        return "login";
    }
}
