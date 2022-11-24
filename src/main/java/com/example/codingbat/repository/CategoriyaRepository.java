package com.example.codingbat.repository;

import com.example.codingbat.entity.Categoriya;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriyaRepository extends JpaRepository<Categoriya, Integer> {
    boolean existsByNomiAndTillarId(String nomi, Integer tillar_id);
}
