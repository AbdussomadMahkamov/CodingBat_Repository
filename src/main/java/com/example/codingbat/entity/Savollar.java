package com.example.codingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Savollar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomi;
    private String matni;
    private String yechim;
    private String funksiya;
    private String yordam;
    @OneToOne
    private Namuna namuna;
    @OneToOne
    private Tillar tillar;
}
