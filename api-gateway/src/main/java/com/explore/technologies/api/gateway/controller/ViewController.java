package com.explore.technologies.api.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class ViewController {

    private String viewServiceUrl = "https://view-service.onrender.com";
    private String localViewServiceUrl = "http://localhost:8081";

    @GetMapping
    public String getIndexView(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String viewContent = restTemplate.getForObject(viewServiceUrl + "/view/index", String.class);
        model.addAttribute("viewContent", viewContent);
        return "html/display-view";
    }
}
