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

import com.example.gymdemo.model.Perfil;
import com.example.gymdemo.service.PerfilService;

@RestController
@RequestMapping("api/v1/perfilcontroller")
public class PerfilController {
    @Autowired
    private PerfilService perfilService;

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crear(@RequestBody(required = true) Perfil perfil) {
        String nombre = perfil.getName();
        if (nombre == null || "".equals(nombre)) {
            return new ResponseEntity<>("El campo nombre no puede estar vacío", HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<Long> response = perfilService.perfil(nombre);
        if (response.getStatusCode() == HttpStatus.CONFLICT) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("La descripcion:" + nombre + "ya existe");
        } else if (response.getStatusCode() == HttpStatus.CREATED) {
            Long idPerfil = response.getBody();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Se creo correctamente el Perfil: " + perfil.getName()
                            + " con el id: "
                            + idPerfil);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list() {
        ResponseEntity<List<Perfil>> response = perfilService.list();
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
