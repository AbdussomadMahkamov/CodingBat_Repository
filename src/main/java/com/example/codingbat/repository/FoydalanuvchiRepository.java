package com.example.codingbat.repository;

import com.example.codingbat.entity.Foydalanuvchi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoydalanuvchiRepository extends JpaRepository<Foydalanuvchi, Integer> {
    boolean existsByEmail(String email);
}
