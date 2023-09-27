package com.example.gymdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.DetalleFactura;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {

}
