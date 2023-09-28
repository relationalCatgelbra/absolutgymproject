package com.example.gymdemo.model;

import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String nombre;
    @Column(length =  20)
    private String apellidoPaterno; //varchar(20)
    @Column(length =  20)
    private String apellidoMaterno; //varchar(20)
    
    @Column(length =  1)
    private Character cedula; //char(11)
    @Column(length =  1)
    private Character genero; //Char(11)
    private Date fechaNacimiento;
    private Integer edad;
    @Column(name = "fechaIngreso", updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar fechaIngreso;
    @Column(length =  20)
    private String telefono;
    @Column(length =  20)
    private String celular;
    @Column(length =  45)
    private String email;
    @Column(length =  20)
    private String personaContacto;
    @Column(length =  20)
    private String telefonoContacta;
    @Column(length =  3)
    private Integer estado;
}