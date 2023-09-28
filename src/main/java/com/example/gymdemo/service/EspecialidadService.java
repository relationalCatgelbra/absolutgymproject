package com.example.gymdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.gymdemo.model.Especialidad;
import com.example.gymdemo.repository.EspecialidadRepository;

import jakarta.transaction.Transactional;

@Service
public class EspecialidadService {
    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Transactional
    public ResponseEntity<Long> newEspe(String nombre) {
        if (especialidadRepository.existsByName(nombre)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Especialidad especialidad = Especialidad.builder()
                .name(nombre)
                .build();
        Especialidad nuevo = especialidadRepository.save(especialidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo.getIdEspecialidad());
    }

    @Transactional
    public ResponseEntity<List<Especialidad>> list() {
        if (especialidadRepository.findAll() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(especialidadRepository.findAll());
    }

}
