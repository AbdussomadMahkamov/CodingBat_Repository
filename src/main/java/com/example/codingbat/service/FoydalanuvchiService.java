package com.example.codingbat.service;

import com.example.codingbat.entity.Foydalanuvchi;
import com.example.codingbat.payload.ApiRespons;
import com.example.codingbat.repository.FoydalanuvchiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoydalanuvchiService {
    @Autowired
    FoydalanuvchiRepository foydalanuvchiRepository;

    public ApiRespons addFoydalanuvchi(Foydalanuvchi foydalanuvchi) {
        boolean b = foydalanuvchiRepository.existsByEmail(foydalanuvchi.getEmail());
        if (b){
            return new ApiRespons("Bazda bunday foydalanuvchi avvaldan mavjud", false);
        }
        foydalanuvchiRepository.save(foydalanuvchi);
        return new ApiRespons("Foydalanuvchi muvoffaqiyatli saqlandi", true);
    }

    public ApiRespons editFoydalanuvchi(Integer id, Foydalanuvchi foydalanuvchi) {
        Optional<Foydalanuvchi> byId = foydalanuvchiRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Bazada bunday idda ma'lumot toplmadi", false);
        }
        Foydalanuvchi users=byId.get();
        users.setParol(foydalanuvchi.getParol());
        foydalanuvchiRepository.save(users);
        return new ApiRespons("Ma'lumotlar muvoffaqiyatli tahrirlandi", true);
    }

    public ApiRespons readFoydalanuvchi() {
        List<Foydalanuvchi> list=foydalanuvchiRepository.findAll();
        String S="";
        for (Foydalanuvchi foydalanuvchi : list) {
            S+=foydalanuvchi.toString()+"\n";
        }
        return new ApiRespons(S, true);
    }

    public ApiRespons readIdFoydalanuvchi(Integer id) {
        Optional<Foydalanuvchi> byId = foydalanuvchiRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Bazada bunday idda ma'lumot toplmadi", false);
        }
        return new ApiRespons(byId.get().toString(), true);
    }

    public ApiRespons delFoydalanuvchi(Integer id) {
        Optional<Foydalanuvchi> byId = foydalanuvchiRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiRespons("Bazada bunday idda ma'lumot toplmadi", false);
        }
        foydalanuvchiRepository.deleteById(id);
        return new ApiRespons("Ma'lumotlar o'chirildi", true);
    }
}
