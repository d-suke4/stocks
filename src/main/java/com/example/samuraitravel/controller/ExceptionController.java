package com.example.samuraitravel.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.example.samuraitravel.model.ErrorResponses;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ExceptionController {
    @Autowired
    private ObjectMapper objectMapper;	
    
    @ExceptionHandler(HttpStatusCodeException.class)
    public ErrorResponses httpStatusCodeException(HttpStatusCodeException e) throws IOException {
        return objectMapper.readValue(e.getResponseBodyAsString(), ErrorResponses.class);
    }    
}
