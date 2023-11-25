package com.example.samuraitravel.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.samuraitravel.entity.Stocks;
import com.example.samuraitravel.form.StocksEditForm;
import com.example.samuraitravel.form.StocksRegisterForm;
import com.example.samuraitravel.model.ListedInfoResponses;
import com.example.samuraitravel.repository.StocksRepository;

@Service
public class StocksService {
    private final StocksRepository stocksRepository;    
    private static final String URL = "https://api.jquants.com/v1/listed/info?code="; 

    public StocksService(StocksRepository stocksRepository) {
        this.stocksRepository = stocksRepository;   
    }    
    
    public ListedInfoResponses get(String idToken, String id) {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.set("Authorization", "Bearer " + idToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ListedInfoResponses> responseEntity = restTemplate.exchange(URL + id, HttpMethod.GET, entity, ListedInfoResponses.class);

        return responseEntity.getBody();
    }

    @Transactional
    public void create(StocksRegisterForm stocksEditForm, String idToken, String id) {
        ListedInfoResponses listedInfoResponses = get(idToken, id);
        if (listedInfoResponses != null && !listedInfoResponses.getInfo().isEmpty()) {
            ListedInfoResponses.Info info = listedInfoResponses.getInfo().get(0); 

            Stocks stocks = new Stocks();    
            stocks.setId(stocksEditForm.getId());                
            stocks.setAcquisitionPrice(stocksEditForm.getAcquisitionPrice());
            stocks.setStockNumber(stocksEditForm.getStockNumber());
            stocks.setTotalAmount(stocksEditForm.getAcquisitionPrice() * stocksEditForm.getStockNumber());
            stocks.setCode(info.getCode());
            stocks.setCompanyName(info.getCompanyName());
            stocks.setSector17Code(info.getSector17Code());
            stocks.setSector17CodeName(info.getSector17CodeName());
            stocks.setSector33Code(info.getSector33Code());
            stocks.setSector33CodeName(info.getSector33CodeName());
            stocks.setScaleCategory(info.getScaleCategory());
            stocks.setMarketCode(info.getMarketCode());
            stocks.setMarketCodeName(info.getMarketCodeName());
                    
            stocksRepository.save(stocks);
        }
    }     
    
    @Transactional
    public void update(StocksEditForm stocksEditForm) {
        Stocks stocks = stocksRepository.getOne(stocksEditForm.getId());
             
        stocks.setAcquisitionPrice(stocksEditForm.getAcquisitionPrice());
        stocks.setStockNumber(stocksEditForm.getStockNumber());
       
        stocksRepository.save(stocks);
        
    }   
}