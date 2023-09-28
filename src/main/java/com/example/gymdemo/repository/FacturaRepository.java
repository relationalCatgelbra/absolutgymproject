package com.example.gymdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
