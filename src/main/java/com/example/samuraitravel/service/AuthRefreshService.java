package com.example.samuraitravel.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.samuraitravel.model.AuthRefreshResponses;

@Service
public class AuthRefreshService {
    private static final String URL = "https://api.jquants.com/v1/token/auth_refresh?refreshtoken=";
    public AuthRefreshResponses get(String refreshtoken) {
      RestTemplate restTemplate = new RestTemplate();
      AuthRefreshResponses respons = restTemplate.postForObject(URL.concat(refreshtoken), 
          null, AuthRefreshResponses.class);
          return respons;
    }
}
