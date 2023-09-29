package com.example.gymdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.gymdemo.DTO.UsuarioDTO;
import com.example.gymdemo.model.Especialidad;
import com.example.gymdemo.model.Perfil;
import com.example.gymdemo.model.Usuario;
import com.example.gymdemo.repository.EspecialidadRepository;
import com.example.gymdemo.repository.PerfilRepository;
import com.example.gymdemo.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public ResponseEntity<Long> crearUser(UsuarioDTO uDto) {
        if (usuarioRepository.existsByusername(uDto.getUsername())
                || usuarioRepository.existsByemail(uDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        Especialidad especialidad = especialidadRepository.findByname(uDto.getNombreEspecialidad());
        Perfil perfil = perfilRepository.findByname(uDto.getNombrePerfil());

        if (especialidad == null || perfil == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Usuario u = Usuario.builder()
                .nombre(uDto.getNombre())
                .apellido(uDto.getApellido())
                .email(uDto.getEmail())
                .telefono(uDto.getTelefono())
                .celular(uDto.getCelular())
                .cargo(uDto.getCargo())
                .username(uDto.getUsername())
                .password(uDto.getPassword())
                .estado(true)
                .especialidad(especialidad)
                .perfil(perfil)
                .build();

        Usuario usuarionuevo = usuarioRepository.save(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarionuevo.getIdUsuario());

    }
}
