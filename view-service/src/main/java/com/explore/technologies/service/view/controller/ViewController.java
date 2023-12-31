package com.explore.technologies.service.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/index")
    public String getIndexView(Model model) {
        return "html/index";
    }

    @GetMapping("/main")
    public String getMainView() {
        return "html/main";
    }
}
