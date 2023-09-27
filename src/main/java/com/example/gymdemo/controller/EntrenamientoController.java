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

import com.example.gymdemo.model.Entrenamiento;
import com.example.gymdemo.service.EntrenamientoService;

@RestController
@RequestMapping("api/v1/entrenamiento")
public class EntrenamientoController {

    @Autowired
    private EntrenamientoService entrenamientoService;

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearE(@RequestBody(required = true) Entrenamiento entrenamiento) {
        String descripcion = entrenamiento.getDescripcion();
        if (descripcion == null || "".equals(descripcion)) {
            return new ResponseEntity<>("El campo descripción no puede estar vacío", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<Long> response = entrenamientoService.newEntre(descripcion);
        if (response.getStatusCode() == HttpStatus.CONFLICT) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("La descripcion:" + descripcion + "ya existe");
        } else if (response.getStatusCode() == HttpStatus.CREATED) {
            Long idEntre = response.getBody();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Se creo correctamente el tipo de entrenamiento: " + entrenamiento.getDescripcion()
                            + ", con el id: "
                            + idEntre);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list() {
        ResponseEntity<List<Entrenamiento>> response = entrenamientoService.list();
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
