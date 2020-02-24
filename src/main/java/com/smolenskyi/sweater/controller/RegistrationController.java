package com.smolenskyi.sweater.controller;

import java.util.Collections;
import java.util.Map;

import com.smolenskyi.sweater.domain.Role;
import com.smolenskyi.sweater.domain.User;
import com.smolenskyi.sweater.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFormDb = userRepo.findByUsername(user.getUsername());
        if(userFormDb != null) {
            model.put("message", "Такий користувач вже існує.");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
