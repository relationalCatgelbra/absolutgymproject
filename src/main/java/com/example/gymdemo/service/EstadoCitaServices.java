package com.example.gymdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.gymdemo.model.EstadoCita;
import com.example.gymdemo.repository.EstadoCitaRepository;

import jakarta.transaction.Transactional;

@Service
public class EstadoCitaServices {

    @Autowired
    private EstadoCitaRepository estadoCitaRepository;

    @Transactional
    public ResponseEntity<Long> newEstado(String nombre) {
        if (estadoCitaRepository.existsBydescripcion(nombre)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        EstadoCita cita = EstadoCita.builder()
                .descripcion(nombre)
                .build();

        EstadoCita nuevoEstado = estadoCitaRepository.save(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstado.getIdEstadoCita());
    }

    @Transactional
    public ResponseEntity<List<EstadoCita>> list() {
        if (estadoCitaRepository.findAll() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(estadoCitaRepository.findAll());
    }

}
