package br.com.iwt.pizzaria.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iwt.pizzaria.domain.model.Cliente;
import br.com.iwt.pizzaria.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	ClienteRepository repositorio;
	
	public Cliente adicionar(Cliente cliente) {
		return repositorio.save(cliente);
	}
	
	
	public List<Cliente> listarTodos(){
		return repositorio.findAll();
	}
	
	public Optional<Cliente> listarPorId(UUID id) {
		return repositorio.findById(id);
	}
	
	public void deletar(Cliente cliente) {
		repositorio.delete(cliente);
	}
}
