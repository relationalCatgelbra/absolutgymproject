package com.example.gymdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.gymdemo.model.Entrenamiento;
import com.example.gymdemo.repository.EntrenamientoRepository;

import jakarta.transaction.Transactional;

@Service
public class EntrenamientoService {
    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    @Transactional
    public ResponseEntity<Long> newEntre(String nombre) {
        if (entrenamientoRepository.existsBydescripcion(nombre)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Entrenamiento entrernamiento = Entrenamiento.builder()
                .descripcion(nombre)
                .build();

        Entrenamiento nuevoEntre = entrenamientoRepository.save(entrernamiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEntre.getIdEntrenamiento());
    }

    @Transactional
    public ResponseEntity<List<Entrenamiento>> list() {
        if (entrenamientoRepository.findAll() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(entrenamientoRepository.findAll());
    }

}
