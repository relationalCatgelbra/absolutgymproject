package com.example.gymdemo.model;

import java.sql.Time;
import java.util.Date;

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
@Table(name = "ValoracionFisica")
public class ValoracionFisica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCitaMedica;
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente idCliente;// 10
    @ManyToOne
    @JoinColumn(name = "idEstadoCita")
    private EstadoCita estadoCita;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "idDiagnostico")
    private Diagnostico diagnostico;
    @ManyToOne
    @JoinColumn(name = "idEntrenamiento")
    private Entrenamiento entrenamiento;
    @ManyToOne
    @JoinColumn(name = "idRevision")
    private RevisionOrganosSistema revisionOrganosSistema;
    @ManyToOne
    @JoinColumn(name = "idExamenFisico")
    private ExamenFisicoRegional examenFisicoRegional;
    @ManyToOne
    @JoinColumn(name = "idSignosVitales")
    private SignosVitales idSignosVitales;// 10
    private Date fechaCita;
    private Time horaCita;
    private Time horaInicioAtencion;
    private Time horaFinAtencion;
    @Column(length = 255)
    private String observaciones;
}
