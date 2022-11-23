package com.example.codingbat.controller;

import com.example.codingbat.entity.Tillar;
import com.example.codingbat.payload.ApiRespons;
import com.example.codingbat.service.TillarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Tillar")
public class TillarController {
    @Autowired
    TillarService tillarService;
    @PostMapping("/addTillar")
    public HttpEntity<?> AddTillar(@RequestBody Tillar tillar){
        ApiRespons apiRespons=tillarService.addTillar(tillar);
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.ALREADY_REPORTED).body(apiRespons.getXabar());
    }
    @PutMapping("/editTillar/{id}")
    public HttpEntity<?> EditTillar(@PathVariable Integer id, @RequestBody Tillar tillar){
        ApiRespons apiRespons=tillarService.editTillar(id, tillar);
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }
    @GetMapping("/readTillar")
    public HttpEntity<?> ReadTillar(){
        ApiRespons apiRespons=tillarService.readTillar();
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }
    @GetMapping("/idreadTillar/{id}")
    public HttpEntity<?> IdReadTillar(@PathVariable Integer id){
        ApiRespons apiRespons=tillarService.idReadTillar(id);
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }
    @DeleteMapping("/deleteTillar/{id}")
    public HttpEntity<?> DeleteTillar(@PathVariable Integer id){
        ApiRespons apiRespons=tillarService.deleteTillar(id);
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }
}
