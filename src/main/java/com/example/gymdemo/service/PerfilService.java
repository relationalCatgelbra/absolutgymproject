package com.example.gymdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.gymdemo.model.Perfil;
import com.example.gymdemo.repository.PerfilRepository;

import jakarta.transaction.Transactional;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;

    @Transactional
    public ResponseEntity<Long> perfil(String nombre) {
        if (perfilRepository.existsByName(nombre)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Perfil perfil = Perfil.builder()
                .name(nombre)
                .build();

        Perfil nuevo = perfilRepository.save(perfil);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo.getIdPerfil());
    }

    @Transactional
    public ResponseEntity<List<Perfil>> list() {
        if (perfilRepository.findAll() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(perfilRepository.findAll());
    }
}
