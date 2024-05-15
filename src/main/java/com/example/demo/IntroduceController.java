package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


@Controller
public class IntroduceController {
    @GetMapping("/introduce")
    public String introduce(@RequestParam(name="name", required = false, defaultValue = "Anonymous") String name, Model model) {
        model.addAttribute("name", name);
        return "introduce";
    }
}
