package com.example.gymdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.ExamenFisicoRegional;

@Repository
public interface ExamenFisicoRegionalRepository extends JpaRepository<ExamenFisicoRegional, Long> {

}
