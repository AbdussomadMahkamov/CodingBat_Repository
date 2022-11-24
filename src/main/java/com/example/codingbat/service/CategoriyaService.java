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
import java.util.Optional;

@Service
public class CategoriyaService {
    @Autowired
    CategoriyaRepository categoriyaRepository;
    @Autowired
    TillarRepository tillarRepository;

    public ApiRespons addCategoriya(CategoriyaDto dto) {
        Optional<Tillar> byId = tillarRepository.findById(dto.getTilId());
        if (byId.isPresent()){
            boolean b = categoriyaRepository.existsByNomiAndTillarId(dto.getNomi(), dto.getTilId());
            if (!b){
                Categoriya categoriya=new Categoriya();
                categoriya.setNomi(dto.getNomi());
                categoriya.setIzoh(dto.getIzoh());
                categoriya.setYulduzcha(dto.getYulduzcha());
                categoriya.setTillar(byId.get());
                categoriyaRepository.save(categoriya);
                return new ApiRespons("Ma'lumotlar bazaga saqlandi", true);

            }
            return new ApiRespons("Bunday kategoriya allaqachon ro'yhatdan o'tgan ", false);
        }
            return new ApiRespons("Bazada mavjud bo'lmagan til kiritildi", false);
    }

    public ApiRespons editCategoriya(Integer id, CategoriyaDto dto) {
        Optional<Categoriya> byId = categoriyaRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Bazada bunday idda categoriya ma'lumoti topilmadi", false);
        }
        Optional<Tillar> byId1 = tillarRepository.findById(dto.getTilId());
        if (!byId1.isPresent()){
            return new ApiRespons("Bunday idda til mavjud emas",false);
        }
        boolean b = categoriyaRepository.existsByNomiAndTillarId(dto.getNomi(), dto.getTilId());
        System.out.println(b);
        if (!b){
            Categoriya categoriya=byId.get();
            categoriya.setNomi(dto.getNomi());
            categoriya.setIzoh(dto.getIzoh());
            categoriya.setYulduzcha(dto.getYulduzcha());
            categoriya.setTillar(byId1.get());
            categoriyaRepository.save(categoriya);
            return new ApiRespons("Ma'lumotlar tahrirlandi", true);
        }
        return new ApiRespons("Bunday ma'lumot avvaldan mavjud", false);
    }

    public ApiRespons readCategoriya() {
        List<Categoriya> list=categoriyaRepository.findAll();
        String ss="";
        for (Categoriya categoriya : list) {
            ss+=categoriya.toString()+"\n";
        }
        return new ApiRespons(ss, true);
    }

    public ApiRespons readIdCategoriya(Integer id) {
        Optional<Categoriya> byId = categoriyaRepository.findById(id);
        if (byId.isPresent()){
            return new ApiRespons(byId.toString(), true);
        }
        return new ApiRespons("Bazada bunday idda ma'lumot mavjud emas", false);
    }

    public ApiRespons delCategoriya(Integer id) {
        Optional<Categoriya> byId = categoriyaRepository.findById(id);
        if (byId.isPresent()){
            categoriyaRepository.deleteById(id);
            return new ApiRespons("Bazdan ma'lumotlar o'chirildi", true);
        }
        return new ApiRespons("Bazada bunday idda ma'lumot mavjud emas", false);
    }
}
