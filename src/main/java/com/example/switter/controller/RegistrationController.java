package com.example.switter.controller;
import com.example.switter.domain.User;
import com.example.switter.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
                          @Valid User user,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(result);
            model.mergeAttributes(errors);
            return "registration";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping(value = "/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
         boolean isActivated = userService.activated(code);
         if (isActivated) {
             model.addAttribute("message", "user successfully activated");
         } else {
             model.addAttribute("message", "user not found");
         }
        return "login";
    }
}
