package com.example.gymdemo.model;

import java.io.Serializable;

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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Revisionorganossistema")
public class RevisionOrganosSistema implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRevision;
    @Column(length = 1)
    private Integer ROS1;
    @Column(length = 1)
    private Integer ROS2;
    @Column(length = 1)
    private Integer ROS3;
    @Column(length = 1)
    private Integer ROS4;
    @Column(length = 1)
    private Integer ROS5;
    @Column(length = 1)
    private Integer ROS6;
    @Column(length = 1)
    private Integer ROS7;
    @Column(length = 1)
    private Integer ROS8;
    @Column(length = 1)
    private Integer ROS9;
    @Column(length = 1)
    private Integer ROS10;

}