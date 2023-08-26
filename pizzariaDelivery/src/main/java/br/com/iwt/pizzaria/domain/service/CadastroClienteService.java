package br.com.iwt.pizzaria.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.iwt.pizzaria.api.assembler.ClienteAssembler;
import br.com.iwt.pizzaria.api.model.ClienteModel;
import br.com.iwt.pizzaria.api.model.input.ClienteInput;
import br.com.iwt.pizzaria.domain.exception.EntidadeNaoEcontradaException;
import br.com.iwt.pizzaria.domain.model.Cliente;
import br.com.iwt.pizzaria.domain.model.Compra;
import br.com.iwt.pizzaria.domain.model.filter.ClienteFilter;
import br.com.iwt.pizzaria.domain.repository.ClienteRepository;
import br.com.iwt.pizzaria.domain.repository.ComprasRepository;
import br.com.iwt.pizzaria.domain.repository.spec.ClienteSpec;

@Service
public class CadastroClienteService {

	@Autowired
	ClienteRepository repositorio;
	
	@Autowired
	ComprasRepository compraRepositorio;
	
	@Autowired
	ClienteAssembler assembler;
	
	public ClienteModel adicionar(ClienteInput clienteInput) {
		
		Cliente cliente = new Cliente();
		
		cliente.setId(clienteInput.getId());
		cliente.setNome(clienteInput.getNome());
		cliente.setEndereco(clienteInput.getEndereco());
		cliente.setCompras(clienteInput.getCompras());
		
		repositorio.save(cliente);
		
		return assembler.toModel(cliente);
	}
	
	public Page<ClienteModel> listarTodos(ClienteFilter filtro,Pageable pageable){
		
		List<ClienteModel> clientes = assembler.toCollectionModel(repositorio.findAll(ClienteSpec.usandoFiltro(filtro),pageable).getContent());
		
		Page<ClienteModel> clientesModelPage = new PageImpl<>(clientes, pageable, clientes.size());
		
		return clientesModelPage;
	}
	
	public Optional<Cliente> listarPorId(UUID id) {
		return repositorio.findById(id);
	}
	
	public ClienteModel listarPorIdOrThrow(UUID id) {
		return assembler.toModel(repositorio.findById(id).orElseThrow(()-> new EntidadeNaoEcontradaException("cliente não encontrado")));
	}
	
	public ClienteModel atualizarCliente(UUID id, ClienteInput clienteInput) {
		
		listarPorIdOrThrow(id);
		
		List<Compra> compras = compraRepositorio.findCompraByClienteId(id);
		
		clienteInput.setId(id);
		if(compras != null)clienteInput.setCompras(compras);
		
		return adicionar(clienteInput);
	}
	
	public void deletar(UUID clienteId) {
					
		listarPorIdOrThrow(clienteId);
		
		repositorio.deleteById(clienteId);
	}
}