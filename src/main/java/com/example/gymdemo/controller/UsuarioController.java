package com.example.gymdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gymdemo.DTO.UsuarioDTO;
import com.example.gymdemo.service.UsuarioService;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearUser(@RequestBody(required = true) UsuarioDTO user) {

        /*
         * String nombre = user.getNombre();
         * String apellido = user.getApellido();
         * String email = user.getEmail();
         * String telefono = user.getTelefono();
         * String celular = user.getCelular();
         * String cargo = user.getCargo();
         * String username = user.getUsername();
         * String password = user.getPassword();
         * String nombreEspecialidad = user.getNombreEspecialidad();
         * String nombrePerfil = user.getNombrePerfil();
         */

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

}
