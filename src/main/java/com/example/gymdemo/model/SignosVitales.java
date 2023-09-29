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
@Table(name = "SignosVitales")
public class SignosVitales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSignosVitales;
    @Column(length = 3)
    private Integer temperatura;
    @Column(length = 3)
    private Integer presionArterialSistolica;
    @Column(length = 3)
    private Integer presionArterialDiastolica;
    @Column(length = 3)
    private Integer pulsoMin;
    @Column(length = 3)
    private Double pesoKg; // tinyint oracle = SMALLINT on pgsql
    @Column(length = 3)
    private Integer frecuenciaRespiratoria;
    @Column(length = 3)
    private Double talla;

}
