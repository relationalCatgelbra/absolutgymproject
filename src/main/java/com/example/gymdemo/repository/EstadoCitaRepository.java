package com.example.gymdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.EstadoCita;

@Repository
public interface EstadoCitaRepository extends JpaRepository<EstadoCita, Long> {

    boolean existsBydescripcion(String descripcion);
}
