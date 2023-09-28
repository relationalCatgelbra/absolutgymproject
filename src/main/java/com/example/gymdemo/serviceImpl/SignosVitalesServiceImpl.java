package com.example.gymdemo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gymdemo.model.SignosVitales;
import com.example.gymdemo.repository.SignosVitalesRepository;

@Service
public class SignosVitalesServiceImpl {
	
	@Autowired
	private SignosVitalesRepository signosVitalesRepository;
	
	public SignosVitales createSignosVitales(SignosVitales signosVitales) {
		return signosVitalesRepository.save(signosVitales);
	
	}

}
