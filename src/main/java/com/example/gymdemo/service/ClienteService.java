package com.example.gymdemo.service;

import java.util.List;

import com.example.gymdemo.model.Cliente;
import org.springframework.http.ResponseEntity;

public interface ClienteService {

	ResponseEntity<Cliente> createCliente(Cliente cliente);

	ResponseEntity<Cliente> getClienteById(Long clienteId);

	ResponseEntity<List<Cliente>> getClientes();

	ResponseEntity<Cliente> updateCliente(Long clienteId, Cliente cliente);

}
