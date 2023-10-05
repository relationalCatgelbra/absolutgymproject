package com.example.gymdemo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        Optional<Usuario> userOptional = usuarioRepository.findById(id);

        if (userOptional.isPresent()) {
            Usuario user = userOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado con el ID: " + id);
    }

    @Transactional
    public ResponseEntity<?> listActive() {
        List<Usuario> listAct = usuarioRepository.findByEstado(true);
        if (listAct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(listAct);
    }

    public Usuario findById(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.orElse(null);
    }

    @Transactional
    public ResponseEntity<Usuario> EditUser(Long id, Map<String, Object> updates) {

        Usuario usuarioExiste = findById(id);/*
                                              * .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                              * "no se encontro usuario"))
                                              */
        if (usuarioExiste == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (updates.containsKey("nombre")) {
            usuarioExiste.setNombre((String) updates.get("nombre"));
        }
        if (updates.containsKey("especialidad")) {
            String nombreEspecialidad = (String) updates.get("especialidad");
            Especialidad especialidad = especialidadRepository.findByname(nombreEspecialidad);
            if (especialidad != null) {
                usuarioExiste.setEspecialidad(especialidad);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la especialidad");
            }

        }
        if (updates.containsKey("perfil")) {
            String nombrePerfil = (String) updates.get("perfil");
            Perfil perfil = perfilRepository.findByname(nombrePerfil);
            if (perfil != null) {
                usuarioExiste.setPerfil(perfil);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el perfil");
            }
        }
        if (updates.containsKey("apellido")) {
            usuarioExiste.setApellido((String) updates.get("apellido"));
        }
        if (updates.containsKey("email")) {
            usuarioExiste.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("telefono")) {
            usuarioExiste.setTelefono((String) updates.get("telefono"));
        }
        if (updates.containsKey("celular")) {
            usuarioExiste.setCelular((String) updates.get("celular"));
        }
        if (updates.containsKey("cargo")) {
            usuarioExiste.setCargo((String) updates.get("cargo"));
        }
        if (updates.containsKey("username")) {
            usuarioExiste.setUsername((String) updates.get("username"));
        }
        if (updates.containsKey("password")) {
            usuarioExiste.setPassword((String) updates.get("password"));
        }
        if (updates.containsKey("estado")) {
            boolean nuevoEstado = (boolean) updates.get("estado");
            usuarioExiste.setEstado(nuevoEstado);
        }

        Usuario user = usuarioRepository.save(usuarioExiste);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
