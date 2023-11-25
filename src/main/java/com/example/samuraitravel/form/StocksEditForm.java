package com.example.samuraitravel.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StocksEditForm {
    @NotBlank(message = "銘柄コードを入力してください。")
    @Size(min=4, max=4, message =  "証券コードは4文字で入力してください。")
    private String id;
    
    @NotNull(message = "取得単価を入力してください。")
    @Min(value = 1, message = "取得単価は1円以上に設定してください。")
    private Integer acquisitionPrice;  
    
    @NotNull(message = "保有株数を入力してください。")
    @Min(value = 1, message = "保有株数は1株に設定してください。")
    private Integer stockNumber;         
}
