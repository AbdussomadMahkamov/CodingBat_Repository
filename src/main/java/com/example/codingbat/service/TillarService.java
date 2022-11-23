package com.example.codingbat.service;

import com.example.codingbat.entity.Tillar;
import com.example.codingbat.payload.ApiRespons;
import com.example.codingbat.repository.TillarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TillarService {
    @Autowired
    TillarRepository tillarRepository;

    public ApiRespons addTillar(Tillar tillar) {
        boolean b = tillarRepository.existsByNomi(tillar.getNomi());
        if (b){
            return new ApiRespons("Bazada bunday nomda til mavjud", false);
        }
        tillarRepository.save(tillar);
        return new ApiRespons("Ma'lumotlar muvoffaqiyatli saqlandi", true);
    }

    public ApiRespons editTillar(Integer id, Tillar tillar) {
        Optional<Tillar> byId = tillarRepository.findById(id);
        if (byId.isPresent()){
            Tillar tillar1=byId.get();
            tillar1.setNomi(tillar.getNomi());
            tillarRepository.save(tillar1);
            return new ApiRespons("Ma'lumotlar tahrirlandi", true);
        }
        return new ApiRespons("Bazda bunday idli ma'lumot topilmadi",false);
    }

    public ApiRespons readTillar() {
        List<Tillar> list=tillarRepository.findAll();
        return new ApiRespons(list.toString(), true);
    }

    public ApiRespons idReadTillar(Integer id) {
        Optional<Tillar> byId = tillarRepository.findById(id);
        if (byId.isPresent()){
            return new ApiRespons(byId.toString(), true);
        }
        return new ApiRespons("Bazada bunday idda ma'lumot mavjud emas",false);
    }

    public ApiRespons deleteTillar(Integer id) {
        Optional<Tillar> byId = tillarRepository.findById(id);
        if (byId.isPresent()){
            tillarRepository.deleteById(id);
            return new ApiRespons("Bazdagi ma'lumot o'chirildi", true);
        }
        return new ApiRespons("Bazada bunday idda ma'lumot mavjud emas",false);
    }
}
