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
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "DatosPersonales")
public class DatosPersonales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDatosPersonales;
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente idCliente;
    @Column(length = 255)
    private String antecedentesPersonales;

    @Column(length = 1)
    private Integer af1;
    @Column(length = 1)
    private Integer af2;
    @Column(length = 1)
    private Integer af3;
    @Column(length = 1)
    private Integer af4;
    @Column(length = 1)
    private Integer af5;
    @Column(length = 1)
    private Integer af6;
    @Column(length = 1)
    private Integer af7;
    @Column(length = 1)
    private Integer af8;
    @Column(length = 1)
    private Integer af9;
    @Column(length = 1)
    private Integer af10;
    @Column(length = 1)
    private Integer af11;
    @Column(length = 1)
    private Integer af12;
    @Column(length = 1)
    private Integer af13;
    @Column(length = 1)
    private Integer af14;
    @Column(length = 1)
    private Integer af15;
    @Column(length = 1)
    private Integer af16;
    @Column(length = 1)
    private Integer af17;
    @Column(length = 1)
    private Integer af18;
    @Column(length = 1)
    private Integer af19;
    @Column(length = 1)
    private Integer af20;
}
