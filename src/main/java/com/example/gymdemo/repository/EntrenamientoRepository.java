package com.example.gymdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.Entrenamiento;

@Repository
public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {
    boolean existsBydescripcion(String nombre);

}
