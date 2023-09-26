package com.example.gymdemo.service;

import java.util.Optional;

import com.example.gymdemo.model.Cliente;

public interface ClienteService {
	
	Cliente createCliente(Cliente cliente);
	
	Optional<Cliente> getClienteById(Long clienteId);
	
	Iterable<Cliente> getClientes();
	
	Cliente updateCliente(Long clienteId, Cliente cliente);
	
	void deleteClienteById(Long clienteId);

}
