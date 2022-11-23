package com.example.codingbat.controller;

import com.example.codingbat.payload.ApiRespons;
import com.example.codingbat.payload.CategoriyaDto;
import com.example.codingbat.service.CategoriyaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Categoriya")
public class CategoriyaController {
    @Autowired
    CategoriyaService categoriyaService;
    @PostMapping("/addCategoriya")
    public HttpEntity<?> CategoriyaAdd(@RequestBody CategoriyaDto dto){
        ApiRespons apiRespons=categoriyaService.addCategoriya(dto);
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.ALREADY_REPORTED).body(apiRespons.getXabar());
    }
    @PutMapping("/editCategoriya/{id}")
    public HttpEntity<?> CategoriyaEdit(@PathVariable Integer id,@RequestBody CategoriyaDto dto){
        ApiRespons apiRespons=categoriyaService.editCategoriya(id, dto);
        return ResponseEntity.status(apiRespons.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiRespons.getXabar());
    }
}
