package com.example.samuraitravel.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StocksRegisterForm {
    @NotNull
    private String id;
    
    @NotNull(message = "取得単価を入力してください。")
    @Min(value = 1, message = "取得単価は1円以上に設定してください。")
    private Integer acquisitionPrice;  
    
    @NotNull(message = "保有株数を入力してください。")
    @Min(value = 1, message = "保有株数は1株に設定してください。")
    private Integer stockNumber;         
}
