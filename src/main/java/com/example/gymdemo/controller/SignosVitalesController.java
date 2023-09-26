package com.example.gymdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.gymdemo.service.SignosVitalesService;

@RestController

public class SignosVitalesController {
	
	@Autowired
	private SignosVitalesService signosVitalesService;
	
	

}
