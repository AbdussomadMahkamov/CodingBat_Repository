package com.example.codingbat.service;

import com.example.codingbat.entity.Namuna;
import com.example.codingbat.entity.Savollar;
import com.example.codingbat.entity.Tillar;
import com.example.codingbat.payload.ApiRespons;
import com.example.codingbat.payload.SavollarDto;
import com.example.codingbat.repository.NamunaRepository;
import com.example.codingbat.repository.SavollarRepository;
import com.example.codingbat.repository.TillarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavollarService {
    @Autowired
    SavollarRepository savollarRepository;
    @Autowired
    NamunaRepository namunaRepository;
    @Autowired
    TillarRepository tillarRepository;

    public ApiRespons addSavollar(SavollarDto dto) {
        boolean b = savollarRepository.existsByNomiAndTillarId(dto.getNomi(), dto.getTilId());
        if (!b){
            Optional<Tillar> byId = tillarRepository.findById(dto.getTilId());
            if (!byId.isPresent()){
                return new ApiRespons("Bazada bunday idda til mavjud emas", false);
            }
            Savollar savollar=new Savollar();
            savollar.setNomi(dto.getNomi());
            savollar.setMatni(dto.getMatni());
            savollar.setYechim(dto.getYechim());
            savollar.setFunksiya(dto.getFunksiya());
            savollar.setYordam(dto.getYordam());
            Namuna namuna=new Namuna();
            namuna.setMatn(dto.getMatn());
            namunaRepository.save(namuna);
            savollar.setNamuna(namuna);
            savollar.setTillar(byId.get());
            savollarRepository.save(savollar);
            return new ApiRespons("Bazaga ma'lumotlar saqlandi",true);
        }
        return new ApiRespons("Bazda avvaldan saqlangan", false);
    }

    public ApiRespons editSavollar(Integer id, SavollarDto dto) {
        Optional<Savollar> byId = savollarRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Bazada bunday idda ma'lumot tolimadi", false);
        }
        Optional<Tillar> byId1 = tillarRepository.findById(dto.getTilId());
        if (!byId1.isPresent()){
            return new ApiRespons("Bazda bunday idli til mavjud emas", false);
        }
        boolean b = savollarRepository.existsByNomiAndTillarId(dto.getNomi(), dto.getTilId());
        if (!b){
            Savollar savollar=byId.get();
            savollar.setNomi(dto.getNomi());
            savollar.setMatni(dto.getMatni());
            savollar.setYechim(dto.getYechim());
            savollar.setFunksiya(dto.getFunksiya());
            savollar.setYordam(dto.getYordam());
            Namuna namuna=savollar.getNamuna();
            namuna.setMatn(dto.getMatn());
            savollar.setNamuna(namuna);
            namunaRepository.save(namuna);
            savollar.setTillar(byId1.get());
            savollarRepository.save(savollar);
            return new ApiRespons("Ma'lumotlar muvoffaqiyatli tahrirlandi", true);
        }
        return new ApiRespons("Bazada bunday ma'lumot oldindan mavjud", false);
    }

    public ApiRespons readSavollar() {
        List<Savollar> list=savollarRepository.findAll();
        String s="";
        for (Savollar savollar : list) {
            s+=savollar.toString()+"\n";
        }
        return new ApiRespons(s, true);
    }

    public ApiRespons readIdSavollar(Integer id) {
        Optional<Savollar> byId = savollarRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Bazada bunday idda ma'lumot mavjud emas", false);
        }
        return new ApiRespons(byId.toString(), true);
    }

    public ApiRespons delSavollar(Integer id) {
        Optional<Savollar> byId = savollarRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Bazada bunday idda ma'lumot mavjud emas", false);
        }
        Integer namunaid=byId.get().getNamuna().getId();
        savollarRepository.deleteById(id);
        namunaRepository.deleteById(namunaid);
        return new ApiRespons("Ba'zadan ma'lumotlar o'chirildi", true);
    }
}
