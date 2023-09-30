package com.example.gymdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.gymdemo.model.Cliente;
import com.example.gymdemo.service.ClienteService;

@RestController
@RequestMapping("/clientes/")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> getAllClientes() {
		return clienteService.getClientes();
	}

	@GetMapping("{id}")
	public ResponseEntity<Cliente> getClienteById(
			@PathVariable("id") Long clienteId) {
		return clienteService.getClienteById(clienteId);
	}

	@PostMapping
	public ResponseEntity<Cliente> createCliente(
			@RequestBody Cliente cliente) {
		return clienteService.createCliente(cliente);
	}

	@PutMapping("{id}")
	public void updateCliente(
			@PathVariable("id") Long clienteId,
			@RequestBody Cliente cliente) {
		clienteService.updateCliente(clienteId, cliente);

	}

}
