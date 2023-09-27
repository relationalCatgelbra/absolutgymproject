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

import com.example.gymdemo.model.EstadoCita;
import com.example.gymdemo.service.EstadoCitaServices;

@RestController
@RequestMapping("api/v1/estadocitacontroller")
public class EstadoCitaController {

    @Autowired
    private EstadoCitaServices estadoCitaServices;

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearE(@RequestBody(required = true) EstadoCita estadoCita) {
        String estado = estadoCita.getDescripcion();
        if (estado == null || "".equals(estado)) {
            return new ResponseEntity<>("El campo descripción no puede estar vacío", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<Long> response = estadoCitaServices.newEstado(estado);
        if (response.getStatusCode() == HttpStatus.CONFLICT) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El estado" + estado + "ya existe");
        } else if (response.getStatusCode() == HttpStatus.CREATED) {
            Long idEstado = response.getBody();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Se creo correctamente el Estado: " + estadoCita.getDescripcion() + " con el id: "
                            + idEstado);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list() {
        ResponseEntity<List<EstadoCita>> response = estadoCitaServices.list();
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
