package com.example.gymdemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByusername(String username);

    boolean existsByemail(String email);

    List<Usuario> findByEstado(boolean estado);

    Optional<Usuario> findByusername(String username);

}
