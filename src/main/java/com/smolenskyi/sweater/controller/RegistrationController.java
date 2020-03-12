package com.smolenskyi.sweater.controller;

import com.smolenskyi.sweater.domain.User;
import com.smolenskyi.sweater.domain.dto.CaptchaResponceDto;
import com.smolenskyi.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&responce";
    @Autowired
    private UserService userService;

    @Value("${recaptcha.secret}")
    private String secret;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/registration")
    public String registration(/*Map<String, Object> model*/) {
//        model.put("message", "");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam(name="password2") String passwordConfirmation,
                          @RequestParam(name = "g-recaptcha-response") String captchaResponce,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model
    ) {
        String url = String.format(CAPTCHA_URL, secret, captchaResponce);
        CaptchaResponceDto responce = restTemplate.postForObject(url, Collections.EMPTY_LIST, CaptchaResponceDto.class);
        if(!responce.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
        }
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirmation);
        if(isConfirmEmpty)
            model.addAttribute("password2Error", "Password confirmation cannot be empty");
        if(user.getPassword() != null && !user.getPassword().equals(passwordConfirmation)){
            model.addAttribute("passwordError", "Password are diferent!");
        }
        if(isConfirmEmpty || bindingResult.hasErrors() || !responce.isSuccess()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if(!userService.addUser(user)) {
            model.addAttribute("usernameError", "Такий користувач вже існує.");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivate = userService.activateUser(code);
        if(isActivate) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated.");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found.");
        }
        return "login";
    }
}
