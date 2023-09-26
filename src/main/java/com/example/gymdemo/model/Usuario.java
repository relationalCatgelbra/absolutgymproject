package com.example.gymdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    @ManyToOne
    @JoinColumn(name = "idEspecialidad")
    private Especialidad especialidad;
    @ManyToOne
    @JoinColumn(name = "idPerfil")
    private Perfil idPerfil;
    @Column(length = 45)
    private String nombre;
    @Column(length = 45)
    private String apellido;
    @Column(length = 45)
    private String email;
    @Column(length = 20)
    private String telefono;
    @Column(length = 45)
    private String celular;
    @Column(length = 45)
    private String cargo;
    @Column(length = 45)
    private String username;
    private String password;
}
