package com.example.switter.controller;

import com.example.switter.domain.Message;
import com.example.switter.domain.User;
import com.example.switter.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class GreetingController {

    @Autowired
    private MessageRepo repo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping(value = "/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping(value = "/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = repo.findAll();
        if (filter != null && !filter.isEmpty()) {
            messages = repo.findByTag(filter);
        } else {
            messages = repo.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping(value = "/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult result,
            Model model,
            @RequestParam("file") MultipartFile file) throws IOException {
            message.setAuthor(user);
        if (result.hasErrors()) {
            Map<String, String> errorMap = ControllerUtils.getErrors(result);
            model.mergeAttributes(errorMap);
            model.addAttribute("message", message);
        } else {
            if (file != null) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                    String uid = UUID.randomUUID().toString();
                    String resultFileName = uid + "." + file.getOriginalFilename();
                    file.transferTo(new File(uploadPath + "/" + resultFileName));
                    message.setFilename(resultFileName);
                }
            }
            repo.save(message);
        }
        Iterable<Message> messages = repo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }
}
