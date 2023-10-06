package com.example.gymdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    boolean existsByName(String name);

    Perfil findByname(String name);
}
