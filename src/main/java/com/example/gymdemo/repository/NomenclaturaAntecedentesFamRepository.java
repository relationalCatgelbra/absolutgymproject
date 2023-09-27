package com.example.gymdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.NomenclaturaAntecedentesFamiliares;

@Repository
public interface NomenclaturaAntecedentesFamRepository extends JpaRepository<NomenclaturaAntecedentesFamiliares, Long> {

}
