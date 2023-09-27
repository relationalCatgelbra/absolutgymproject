package com.example.gymdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gymdemo.model.RevisionOrganosSistema;

@Repository
public interface RevisionOrganosSistemaRepository extends JpaRepository<RevisionOrganosSistema, Long> {

}
