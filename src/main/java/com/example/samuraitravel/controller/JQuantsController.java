package com.example.samuraitravel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.samuraitravel.model.AuthRefreshResponses;
import com.example.samuraitravel.model.RefreshtokenResponses;

import com.example.samuraitravel.repository.StocksRepository;
import com.example.samuraitravel.service.AuthRefreshService;
import com.example.samuraitravel.service.RefreshtokenService;

@RestController
@RequestMapping("api")
public class JQuantsController {
    private final RefreshtokenService refreshtokenService;
    private final AuthRefreshService authRefreshService;
    
    public JQuantsController(RefreshtokenService refreshtokenService, AuthRefreshService authRefreshService, StocksRepository stocksRepository) {
        this.refreshtokenService = refreshtokenService;
        this.authRefreshService = authRefreshService;
    }

    @GetMapping("auth-refresh")
    public String authRefresh() {
        RefreshtokenResponses refreshtoken = refreshtokenService.get();
        AuthRefreshResponses authRefreshResponses = authRefreshService.get(refreshtoken.getRefreshToken());

        // idToken フィールドを取得
        String idToken = authRefreshResponses.getIdToken();
    
        return idToken;
    }

}
