package com.example.gymdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gymdemo.DTO.UsuarioDTO;
import com.example.gymdemo.model.Usuario;
import com.example.gymdemo.service.UsuarioService;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearUser(@RequestBody(required = true) UsuarioDTO user) {

        ResponseEntity<Long> response = usuarioService.crearUser(user);

        if (response.getStatusCode() == HttpStatus.CONFLICT) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe el usuario: " + user.getUsername() + ", o email es repetido: " + user.getEmail());
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El Perfil y la especialidad no deben de ser vacios");
        } else if (response.getStatusCode() == HttpStatus.CREATED) {
            Long usuarioId = response.getBody();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Se creo correctamente al usuario, ID: " + usuarioId + " Username: " + user.getUsername());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        /*
         * if (nombre == null || "".equals(nombre))
         * return new ResponseEntity<>("El campo Nombre es obligatorio",
         * HttpStatus.BAD_REQUEST);
         */
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list() {
        ResponseEntity<List<Usuario>> response = usuarioService.listAllUsers();
        if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sin datos");
        } else if (response.getStatusCode() == HttpStatus.OK) {

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/listid/{id}")
    public ResponseEntity<?> listbyid(@PathVariable Long id) {
        ResponseEntity<?> response = usuarioService.listbyid(id);
        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado por el id: " + id);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(value = "/listActive")
    public ResponseEntity<?> listActive() {
        ResponseEntity<?> response = usuarioService.listActive();
        if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PatchMapping(value = "editUser/{id}")
    public ResponseEntity<?> editUser(@PathVariable(required = true) Long id, Usuario usuario) {
        ResponseEntity<Usuario> response = usuarioService.EditUser(id, usuario);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro el usuario");
        } else if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
