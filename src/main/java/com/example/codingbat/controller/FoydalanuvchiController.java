package com.example.codingbat.controller;

import com.example.codingbat.entity.Foydalanuvchi;
import com.example.codingbat.payload.ApiRespons;
import com.example.codingbat.service.FoydalanuvchiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Foydalanuvchi")
public class FoydalanuvchiController {
    @Autowired
    FoydalanuvchiService foydalanuvchiService;
    @PostMapping("/addFoydalanuvchi")
    public HttpEntity<?> FoydalanuvchiAdd(@RequestBody Foydalanuvchi foydalanuvchi){
        ApiRespons apiRespons=foydalanuvchiService.addFoydalanuvchi(foydalanuvchi);
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.ALREADY_REPORTED).body(apiRespons.getXabar());
    }
    @PutMapping("/editFoydalanuvchi/{id}")
    public HttpEntity<?> FoydalanuvchiEdit(@PathVariable Integer id, @RequestBody Foydalanuvchi foydalanuvchi){
        ApiRespons apiRespons=foydalanuvchiService.editFoydalanuvchi(id, foydalanuvchi);
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }
    @GetMapping("/readFoydalanuvchi")
    public HttpEntity<?> FoydalanuvchiRead(){
        ApiRespons apiRespons=foydalanuvchiService.readFoydalanuvchi();
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.ALREADY_REPORTED).body(apiRespons.getXabar());
    }
    @GetMapping("/readIdFoydalanuvchi/{id}")
    public HttpEntity<?> FoydalanuvchiIdRead(@PathVariable Integer id){
        ApiRespons apiRespons=foydalanuvchiService.readIdFoydalanuvchi(id);
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.ALREADY_REPORTED).body(apiRespons.getXabar());
    }
    @DeleteMapping("/delFoydalanuvchi/{id}")
    public HttpEntity<?> FoydalanuvchiDel(@PathVariable Integer id){
        ApiRespons apiRespons=foydalanuvchiService.delFoydalanuvchi(id);
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.ALREADY_REPORTED).body(apiRespons.getXabar());
    }
}
