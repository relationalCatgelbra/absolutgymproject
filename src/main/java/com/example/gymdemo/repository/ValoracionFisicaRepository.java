package com.example.gymdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.ValoracionFisica;

@Repository
public interface ValoracionFisicaRepository extends JpaRepository<ValoracionFisica, Long> {

}
