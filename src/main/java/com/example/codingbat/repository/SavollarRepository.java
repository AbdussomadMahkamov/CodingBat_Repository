package com.example.codingbat.repository;

import com.example.codingbat.entity.Savollar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavollarRepository extends JpaRepository<Savollar,Integer> {
    boolean existsByNomiAndTillarId(String nomi, Integer tillar_id);
}
