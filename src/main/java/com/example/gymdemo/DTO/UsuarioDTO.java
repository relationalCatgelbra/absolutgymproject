package com.example.gymdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String celular;
    private String cargo;
    private String username;
    private String password;
    private String nombreEspecialidad;
    private String nombrePerfil;
}
