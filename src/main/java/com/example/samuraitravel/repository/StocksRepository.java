package com.example.samuraitravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.samuraitravel.dto.SectorAmountDto;
import com.example.samuraitravel.entity.Stocks;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, String> {
    //　id検索 
    Stocks getOne(String id);

    //　idで降順
    List<Stocks> findAllByOrderByIdDesc();

    //　idで昇順
    List<Stocks> findAllByOrderByIdAsc();

    // 合計額を表示
    @Query("SELECT SUM(s.totalAmount) FROM Stocks s")
    Integer sumTotalAmount();

    // Sector17の合計額を表示
    @Query("SELECT NEW com.example.samuraitravel.dto.SectorAmountDto(s.sector17CodeName, SUM(s.totalAmount)) FROM Stocks s GROUP BY s.sector17CodeName ORDER BY SUM(s.totalAmount) DESC")
    List<SectorAmountDto> sumSecter17Amount();

    // Sector33の合計額を表示
    @Query("SELECT NEW com.example.samuraitravel.dto.SectorAmountDto(s.sector33CodeName, SUM(s.totalAmount)) FROM Stocks s GROUP BY s.sector33CodeName ORDER BY SUM(s.totalAmount) DESC")
    List<SectorAmountDto> sumSecter33Amount();
}
