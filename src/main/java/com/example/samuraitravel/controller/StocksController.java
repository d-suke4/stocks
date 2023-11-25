package com.example.samuraitravel.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.dto.SectorAmountDto;
import com.example.samuraitravel.entity.Stocks;
import com.example.samuraitravel.form.StocksEditForm;
import com.example.samuraitravel.form.StocksRegisterForm;
import com.example.samuraitravel.model.AuthRefreshResponses;
import com.example.samuraitravel.model.RefreshtokenResponses;
import com.example.samuraitravel.repository.StocksRepository;
import com.example.samuraitravel.service.AuthRefreshService;
import com.example.samuraitravel.service.RefreshtokenService;
import com.example.samuraitravel.service.StocksService;

@Controller
@RequestMapping("stocks")
public class StocksController {
    private final StocksRepository stocksRepository;
    private final StocksService stocksService;
    private final RefreshtokenService refreshtokenService;
    private final AuthRefreshService authRefreshService;
    private String authRefresh;

    public StocksController(StocksRepository stocksRepository, StocksService stocksService, RefreshtokenService refreshtokenService, AuthRefreshService authRefreshService) {
        this.stocksRepository = stocksRepository;
        this.stocksService = stocksService;
        this.refreshtokenService = refreshtokenService;
        this.authRefreshService = authRefreshService;
        this.authRefresh = authRefresh();
    }

    private String authRefresh() {
        RefreshtokenResponses refreshtoken = refreshtokenService.get();
        AuthRefreshResponses authRefreshResponses = authRefreshService.get(refreshtoken.getRefreshToken());
        // idToken フィールドを取得
        String idToken = authRefreshResponses.getIdToken();
        return idToken;
    }

    @GetMapping
    public String index(@RequestParam(name = "order", required = false) String order, Model model){
        List<Stocks> stocks;
        if(order == null || order.equals("IdAsc")){
            stocks = stocksRepository.findAllByOrderByIdAsc();
        } else {
            stocks = stocksRepository.findAllByOrderByIdDesc();
        }

        List<SectorAmountDto> sectorData = stocksRepository.sumSecter17Amount();

        // Chart.jsに変換
        StringBuilder labels = new StringBuilder("[");
        StringBuilder values = new StringBuilder("[");
        for (int i = 0; i < sectorData.size(); i++) {
            SectorAmountDto sector = sectorData.get(i);
            labels.append("\"").append(sector.getSectorCodeName()).append("\"");
            values.append(sector.getTotalAmount());
        
            // 最後の要素でない場合にのみカンマを追加
            if (i < sectorData.size() - 1) {
                labels.append(", ");
                values.append(", ");
            }
        }
        labels.append("]");
        values.append("]");
        
        String chartData = "{ \"labels\": " + labels.toString() + ", \"values\": " + values.toString() + " }";
        

        model.addAttribute("stocks", stocks);  
        model.addAttribute("order", order);
        model.addAttribute("totalAmount", stocksRepository.sumTotalAmount());
        model.addAttribute("chartData", chartData);


        return "stocks/index";
    }

    @GetMapping("/register")
     public String register(Model model) {
         model.addAttribute("stocksRegisterForm", new StocksRegisterForm());
         return "stocks/register";
    }   
    
    @PostMapping("/create")
    public String create(@ModelAttribute @Validated StocksRegisterForm stocksRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            return "stocks/register";
        }
         
        stocksService.create(stocksRegisterForm, this.authRefresh, stocksRegisterForm.getId());
        redirectAttributes.addFlashAttribute("successMessage", "保有株式を登録しました。");    
         
        return "redirect:/stocks";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable(name = "id") String id, Model model) {
        Stocks stocks = stocksRepository.getReferenceById(id);
        StocksEditForm stocksEditForm = new StocksEditForm(stocks.getId(), stocks.getAcquisitionPrice(), stocks.getStockNumber());
        
        model.addAttribute("stocksEditForm", stocksEditForm);
        
        return "stocks/edit";
    }    

    @PostMapping("/{id}/update")
    public String update(@ModelAttribute @Validated StocksEditForm stocksEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {        
        if (bindingResult.hasErrors()) {
            return "stocks/edit";
        }
        
        stocksService.update(stocksEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "株式情報を編集しました。");
        
        return "redirect:/stocks";
    }  
    
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable(name = "id") String id, RedirectAttributes redirectAttributes) {        
        stocksRepository.deleteById(id);
                
        redirectAttributes.addFlashAttribute("successMessage", "株式を削除しました。");
        
        return "redirect:/stocks";
    }  
}
