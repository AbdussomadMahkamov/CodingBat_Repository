package com.example.codingbat.repository;

import com.example.codingbat.entity.Tillar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TillarRepository extends JpaRepository<Tillar,Integer> {
    boolean existsByNomi(String nomi);
}
