package com.explore.technologies.api.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Controller
public class ViewController {

    private String viewServiceUrl = "https://view-service.onrender.com";
    private String localViewServiceUrl = "http://localhost:8081";

    @Value("${api.gateway.client.id}")
    private String apiGatewayClientId;

    @GetMapping
    public String getIndexView(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        headers.setAccept(Collections.singletonList(MediaType.TEXT_HTML));
        headers.set("X-Client-Id", apiGatewayClientId);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(localViewServiceUrl + "/view/index", HttpMethod.GET, request, String.class, 1);

        if (response.getStatusCode() == HttpStatus.OK) {
            model.addAttribute("viewContent", response.getBody());
            return "html/display-view";
        }
        else {
            return "html/internal-server-error";
        }
    }
}
