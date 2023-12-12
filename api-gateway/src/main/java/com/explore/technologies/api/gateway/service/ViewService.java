package com.explore.technologies.api.gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ViewService {

    private String viewServiceUrl = "https://view-service.onrender.com";
    private String localViewServiceUrl = "http://localhost:8081";

    @Value("${api.gateway.client.id}")
    private String apiGatewayClientId;

    public String getViewByUri(String uri) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        headers.setAccept(Collections.singletonList(MediaType.TEXT_HTML));
        headers.set("X-Client-Id", apiGatewayClientId);

        HttpEntity request = new HttpEntity(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(viewServiceUrl + uri, HttpMethod.GET, request, String.class, 1);
        return response.getBody();
    }
}
