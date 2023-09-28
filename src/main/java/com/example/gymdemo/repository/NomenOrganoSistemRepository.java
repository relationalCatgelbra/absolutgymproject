package com.example.gymdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.NomenclaturaOrganosSistema;

@Repository
public interface NomenOrganoSistemRepository extends JpaRepository<NomenclaturaOrganosSistema, Long> {

}
