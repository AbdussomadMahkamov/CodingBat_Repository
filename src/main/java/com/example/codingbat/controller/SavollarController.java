package com.example.codingbat.controller;

import com.example.codingbat.payload.ApiRespons;
import com.example.codingbat.payload.SavollarDto;
import com.example.codingbat.service.SavollarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Savollar")
public class SavollarController {
    @Autowired
    SavollarService savollarService;
    @PostMapping("addSavollar")
    public HttpEntity<?> SavolAdd(@RequestBody SavollarDto dto){
        ApiRespons apiRespons=savollarService.addSavollar(dto);
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.ALREADY_REPORTED).body(apiRespons.getXabar());
    }
    @PutMapping("/editSavollar/{id}")
    public HttpEntity<?> SavolEdit(@PathVariable Integer id, @RequestBody SavollarDto dto){
        ApiRespons apiRespons=savollarService.editSavollar(id, dto);
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }
    @GetMapping("/readSavollar")
    public HttpEntity<?> SavolRead(){
        ApiRespons apiRespons=savollarService.readSavollar();
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }
    @GetMapping("/readIdSavollar/{id}")
    public HttpEntity<?> SavolIdRead(@PathVariable Integer id){
        ApiRespons apiRespons=savollarService.readIdSavollar(id);
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }
    @DeleteMapping("/delSavollar/{id}")
    public HttpEntity<?> SavolDel(@PathVariable Integer id){
        ApiRespons apiRespons=savollarService.delSavollar(id);
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }
}
