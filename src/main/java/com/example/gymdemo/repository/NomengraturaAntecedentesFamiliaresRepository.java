package com.example.gymdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.NomengraturaAntecedentesFamiliares;

@Repository
public interface NomengraturaAntecedentesFamiliaresRepository
        extends JpaRepository<NomengraturaAntecedentesFamiliares, Long> {

}
