package com.example.gymdemo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Transactional
    public ResponseEntity<List<Usuario>> listAllUsers() {
        if (usuarioRepository.findAll() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }

    @Transactional
    public ResponseEntity<?> listbyid(Long id) {
        List<Usuario> user = usuarioRepository.findAll();
        List<Usuario> findbyid = user.stream()
                .filter(u -> u.getIdUsuario().equals(id))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(findbyid);
    }

    /*
     * Optional<Usuario> userOptional = usuarioRepository.findById(id);
     * 
     * if (userOptional.isPresent()) {
     * Usuario user = userOptional.get();
     * return ResponseEntity.status(HttpStatus.OK).body(user);
     * }
     * return ResponseEntity.status(HttpStatus.NOT_FOUND).
     * body("Usuario no encontrado con el ID: " + id);
     * }
     */

    @Transactional
    public ResponseEntity<Usuario> EditUser(Long id, Usuario usuario) {
        Usuario usuarioe = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        usuarioe.setEspecialidad(usuario.getEspecialidad());
        usuarioe.setPerfil(usuario.getPerfil());
        usuarioe.setNombre(usuario.getNombre());
        usuarioe.setApellido(usuario.getApellido());
        usuarioe.setEmail(usuario.getEmail());
        usuarioe.setTelefono(usuario.getTelefono());
        usuarioe.setCelular(usuario.getCelular());
        usuarioe.setCargo(usuario.getCargo());
        usuarioe.setUsername(usuario.getUsername());
        usuarioe.setPassword(usuario.getPassword());
        usuarioe.setEstado(usuario.isEstado());

        Usuario save = usuarioRepository.save(usuarioe);
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }

}
