package com.example.gymdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gymdemo.model.Especialidad;
import com.example.gymdemo.service.EspecialidadService;

@RestController
@RequestMapping("api/v1/especialidad")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearE(@RequestBody(required = true) Especialidad especialidad) {
        String name = especialidad.getName();
        if (name == null || "".equals(name)) {
            return new ResponseEntity<>("El campo nombre no puede estar vacío", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<Long> response = especialidadService.newEspe(name);
        if (response.getStatusCode() == HttpStatus.CONFLICT) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("La especialidad:" + name + "ya existe");
        } else if (response.getStatusCode() == HttpStatus.CREATED) {
            Long idEsp = response.getBody();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Se creo correctamente la especialidad: " + especialidad.getName()
                            + " con el id: "
                            + idEsp);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list() {
        ResponseEntity<List<Especialidad>> response = especialidadService.list();
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
