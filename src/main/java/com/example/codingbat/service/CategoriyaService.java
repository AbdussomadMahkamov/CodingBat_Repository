package com.example.codingbat.service;

import com.example.codingbat.entity.Categoriya;
import com.example.codingbat.entity.Tillar;
import com.example.codingbat.payload.ApiRespons;
import com.example.codingbat.payload.CategoriyaDto;
import com.example.codingbat.repository.CategoriyaRepository;
import com.example.codingbat.repository.TillarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriyaService {
    @Autowired
    CategoriyaRepository categoriyaRepository;
    @Autowired
    TillarRepository tillarRepository;

    public ApiRespons addCategoriya(CategoriyaDto dto) {
        Categoriya categoriya=new Categoriya();
        categoriya.setNomi(dto.getNomi());
        categoriya.setIzoh(dto.getIzoh());
        categoriya.setYulduzcha(dto.getYulduzcha());
        categoriya.setTillar(dto.getTillarList());
        categoriyaRepository.save(categoriya);
        return new ApiRespons("Ma'lumotlar bazaga saqlandi", true);
    }

    public ApiRespons editCategoriya(Integer id, CategoriyaDto dto) {
        return null;
    }
}
