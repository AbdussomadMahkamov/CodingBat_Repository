package com.example.codingbat.payload;

import com.example.codingbat.entity.Tillar;
import lombok.Data;

import java.util.List;

@Data
public class CategoriyaDto {
    private String nomi,izoh;
    private Integer yulduzcha;
    private List<Tillar> tillarList;

}
