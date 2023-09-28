package com.example.gymdemo.model;

import java.math.BigDecimal;

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

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

@Entity
@Table(name = "DetalleFactura")
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleFactura;
    @ManyToOne
    @JoinColumn(name = "idCobro")
    private Cobros cobro;
    private BigDecimal valorCobro;
    private Integer lineaNumero;
    @ManyToOne
    @JoinColumn(name = "idFactura")
    private Factura factura;

}
