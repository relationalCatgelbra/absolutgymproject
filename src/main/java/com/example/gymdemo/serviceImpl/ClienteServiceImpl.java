package com.example.gymdemo.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gymdemo.exceptions.ClienteNotFoundException;
import com.example.gymdemo.model.Cliente;
import com.example.gymdemo.repository.ClienteRepository;
import com.example.gymdemo.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente createCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteRepository.save(cliente);
	}

	@Override
	public Optional<Cliente> getClienteById(Long clienteId) {
		// TODO Auto-generated method stub
		return clienteRepository.findById(clienteId);
	}

	@Override
	public Iterable<Cliente> getClientes() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

	@Override
	public Cliente updateCliente(Long clienteId, Cliente cliente) {
		// TODO Auto-generated method stub
		
		Cliente existingCliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new ClienteNotFoundException(
						String.format("No Cliente with id %s is available", clienteId)));
		
		
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
					
					return clienteRepository.save(existingCliente);
				
				
				
		
	}

	@Override
	public void deleteClienteById(Long clienteId) {
		// TODO Auto-generated method stub
		
		clienteRepository.findById(clienteId).orElseThrow(
				() -> new ClienteNotFoundException(
						String.format("No Cliente with id %s is available", clienteId)));
		
		clienteRepository.deleteById(clienteId);
	}
	
	
	

}
