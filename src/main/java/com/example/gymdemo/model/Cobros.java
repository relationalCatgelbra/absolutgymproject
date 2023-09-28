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
@Table(name = "Cobros")
public class Cobros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCobro;

    @Column(length = 45)
    private String name;

}
