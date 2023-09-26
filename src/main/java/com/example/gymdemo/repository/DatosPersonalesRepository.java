package com.example.gymdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.DatosPersonales;

@Repository
public interface DatosPersonalesRepository extends JpaRepository<DatosPersonales, Long> {

}
