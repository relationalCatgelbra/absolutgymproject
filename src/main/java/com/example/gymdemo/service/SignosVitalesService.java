package com.example.gymdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gymdemo.model.SignosVitales;
import com.example.gymdemo.repository.SignosVitalesRepository;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

@Service
public class SignosVitalesService {

	@Autowired
	private SignosVitalesRepository signosVitalesRepository;

	public ResponseEntity<SignosVitales> createSignosVitales(SignosVitales signosVitales) {

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(signosVitalesRepository.save(signosVitales));

	}

}