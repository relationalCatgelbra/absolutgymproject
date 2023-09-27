package com.example.gymdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.Cobros;

@Repository
public interface CobrosRepository extends JpaRepository<Cobros, Long> {

}
