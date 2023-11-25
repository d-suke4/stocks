package com.example.samuraitravel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stocks")
@Data
public class Stocks {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "acquisition_price")
    private Integer acquisitionPrice;

    @Column(name = "stock_number")
    private Integer stockNumber;

    @Column(name = "total_amount")
    private Integer totalAmount;

    @Column(name = "code")
    private String code;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "sector17_code")
    private String sector17Code;

    @Column(name = "sector17_code_name")
    private String sector17CodeName;

    @Column(name = "sector33_code")
    private String sector33Code;

    @Column(name = "sector33_code_name")
    private String sector33CodeName;

    @Column(name = "scale_category")
    private String scaleCategory;

    @Column(name = "market_code")
    private String marketCode;

    @Column(name = "market_code_name")
    private String marketCodeName;
}
