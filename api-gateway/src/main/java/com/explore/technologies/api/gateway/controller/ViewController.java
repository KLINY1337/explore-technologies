package com.explore.technologies.api.gateway.controller;

import com.explore.technologies.api.gateway.service.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final ViewService viewService;

    @GetMapping
    public String getIndexView(Model model) {
        String viewContent = viewService.getViewByUri("/view/index");
        model.addAttribute("viewContent", viewContent);
        return "html/display-view";
    }

    @GetMapping("/main")
    public String getMainPageView(Model model) {
        String viewContent = viewService.getViewByUri("/view/main");
        model.addAttribute("viewContent", viewContent);
        return "html/display-view";
    }
}
