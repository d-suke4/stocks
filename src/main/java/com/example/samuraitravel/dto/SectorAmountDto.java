package com.example.samuraitravel.dto;

import lombok.Data;

@Data
public class SectorAmountDto {
    private String sectorCodeName;
    private Long totalAmount;

    // コンストラクタを追加
    public SectorAmountDto(String sectorCodeName, Long totalAmount) {
        this.sectorCodeName = sectorCodeName;
        this.totalAmount = totalAmount;
    }
}
