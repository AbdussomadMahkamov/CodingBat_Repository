package com.example.codingbat.controller;

import com.example.codingbat.payload.ApiRespons;
import com.example.codingbat.payload.JavoblarDto;
import com.example.codingbat.service.JavoblarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Javoblar")
public class JavoblarControlller {
    @Autowired
    JavoblarService javoblarService;
    @PostMapping("/addJavob")
    public HttpEntity<?> JavobAdd(@RequestBody JavoblarDto dto){
        ApiRespons apiRespons=javoblarService.addJavob(dto);
        return ResponseEntity.status(apiRespons.isHolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiRespons.getXabar());
    }
}
