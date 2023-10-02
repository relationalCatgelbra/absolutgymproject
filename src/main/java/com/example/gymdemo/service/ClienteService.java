package com.example.gymdemo.service;

import java.util.List;
import java.util.Optional;

import com.example.gymdemo.model.Cliente;
import com.example.gymdemo.repository.ClienteRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public ResponseEntity<Cliente> createCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		Character clienteCedula = cliente.getCedula();
		if (clienteRepository.existsBycedula(clienteCedula)) {
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body(null);
		}

		Cliente nuevoCliente = clienteRepository.save(cliente);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(nuevoCliente);
	}

	@Transactional
	public ResponseEntity<Cliente> getClienteById(Long clienteId) {
		// TODO Auto-generated method stub

		Optional<Cliente> cliente = clienteRepository.findById(clienteId);

		if (cliente.get() == null) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(null);
		}

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(cliente.get());
	}

	@Transactional
	public ResponseEntity<List<Cliente>> getClientes() {
		// TODO Auto-generated method stub

		if (clienteRepository.findAll() == null) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(null);
		}

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(clienteRepository.findAll());

	}

	@Transactional
	public ResponseEntity<Cliente> updateCliente(Long clienteId, Cliente cliente) {
		// TODO Auto-generated method stub

		Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);

		if (clienteOptional.get() == null) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(null);
		}

		Cliente existingCliente = clienteOptional.get();

		existingCliente.setNombre(cliente.getNombre());
		existingCliente.setApellidoMaterno(cliente.getApellidoMaterno());
		existingCliente.setApellidoPaterno(cliente.getApellidoPaterno());
		existingCliente.setCedula(cliente.getCedula());
		existingCliente.setCelular(cliente.getCelular());
		existingCliente.setEdad(cliente.getEdad());
		existingCliente.setEmail(cliente.getEmail());
		existingCliente.setEstado(cliente.getEstado());
		existingCliente.setFechaIngreso(cliente.getFechaIngreso());
		existingCliente.setFechaNacimiento(cliente.getFechaNacimiento());
		existingCliente.setGenero(cliente.getGenero());
		existingCliente.setPersonaContacto(cliente.getPersonaContacto());
		existingCliente.setTelefonoContacta(cliente.getTelefonoContacta());

		Cliente clienteUpdated = clienteRepository.save(existingCliente);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(clienteUpdated);

	}

}
