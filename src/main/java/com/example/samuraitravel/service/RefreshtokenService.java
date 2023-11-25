package com.example.samuraitravel.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.samuraitravel.model.RefreshtokenRequest;
import com.example.samuraitravel.model.RefreshtokenResponses;


@Service
public class RefreshtokenService {
    private static String URL = "https://api.jquants.com/v1/token/auth_user";
    private static String E_MAIL = "kajiwara0409@gmail.com";
    private static String PASSWORD = "tqTibUnS2D3UPnB";

    
    public RefreshtokenResponses get() {
        RestTemplate restTemplate = new RestTemplate();
        RefreshtokenRequest refreshtokenRequest = new RefreshtokenRequest(E_MAIL, PASSWORD);
        RefreshtokenResponses responses = restTemplate.postForObject(URL, refreshtokenRequest, RefreshtokenResponses.class);
        return responses;
    }
}
