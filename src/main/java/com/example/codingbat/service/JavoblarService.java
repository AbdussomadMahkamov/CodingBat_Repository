package com.example.codingbat.service;

import com.example.codingbat.entity.Foydalanuvchi;
import com.example.codingbat.entity.Javoblar;
import com.example.codingbat.entity.Savollar;
import com.example.codingbat.payload.ApiRespons;
import com.example.codingbat.payload.JavoblarDto;
import com.example.codingbat.repository.FoydalanuvchiRepository;
import com.example.codingbat.repository.JavoblarRepository;
import com.example.codingbat.repository.SavollarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JavoblarService {
    @Autowired
    JavoblarRepository javoblarRepository;
    @Autowired
    SavollarRepository savollarRepository;
    @Autowired
    FoydalanuvchiRepository foydalanuvchiRepository;

    public ApiRespons addJavob(JavoblarDto dto) {
        Optional<Foydalanuvchi> byId = foydalanuvchiRepository.findById(dto.getFoydalanuvchiId());
        if (!byId.isPresent()){
            return new ApiRespons("Bunday id da foydalanuvchi mavjud emas", false);
        }
        Optional<Savollar> byId1 = savollarRepository.findById(dto.getSavolId());
        if (!byId1.isPresent()){
            return new ApiRespons("Bunday id da savol mavjud emas", false);
        }
        Javoblar javob=new Javoblar();
        javob.setSavollar(byId1.get());
        javob.setTogrimi(dto.isTogri());
        javob.setFoydalanuvchi(byId.get());
        javoblarRepository.save(javob);
        return new ApiRespons("Ma'lumotlar saqlandi", true);
    }
}
