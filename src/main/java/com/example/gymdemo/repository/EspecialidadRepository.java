package com.example.gymdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.Especialidad;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
    boolean existsByName(String name);
}
