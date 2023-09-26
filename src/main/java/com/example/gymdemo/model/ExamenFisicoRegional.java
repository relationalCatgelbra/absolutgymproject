package com.example.gymdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "ExamenFisicoRegional")
public class ExamenFisicoRegional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExamenFisico;

    @Column(length = 1)
    private Integer cabeza;
    @Column(length = 1)
    private Integer cuello;
    @Column(length = 1)
    private Integer torax;
    @Column(length = 1)
    private Integer abdomen;
    @Column(length = 1)
    private Integer pelvis;
    @Column(length = 1)
    private Integer extremidades;

}
