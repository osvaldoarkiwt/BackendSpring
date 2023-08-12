package br.com.iwt.pizzaria.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.iwt.pizzaria.api.assembler.ClienteAssembler;
import br.com.iwt.pizzaria.api.model.ClienteModel;
import br.com.iwt.pizzaria.domain.model.Cliente;
import br.com.iwt.pizzaria.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	CadastroClienteService servico;
	
	@Autowired
	ClienteAssembler assembler;
	
	@GetMapping
	public ResponseEntity<List<ClienteModel>> listarClientes(){		
		
		List<ClienteModel> clientes = assembler.toCollectionModel(servico.listarTodos());
		
		return ResponseEntity.ok(clientes);
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> listarCliente(@PathVariable UUID clienteId) {
		Cliente clienteResposta = servico.listarPorId(clienteId).get();
		
		if(clienteResposta != null)	return ResponseEntity.ok(clienteResposta);
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Cliente adicionarCliente(@RequestBody Cliente cliente) {
		return servico.adicionar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizaCliente(@PathVariable UUID clienteId, @RequestBody Cliente cliente) {
		
		Cliente resposta = servico.listarPorId(clienteId).orElse(null);
		
		if(resposta != null) {
			BeanUtils.copyProperties(cliente, resposta,"id");
			
			servico.adicionar(resposta);
			
			return ResponseEntity.ok(resposta);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Cliente> deletaCliente(@PathVariable UUID clienteId){
	
		Cliente resposta = servico.listarPorId(clienteId).orElse(null);
		
		if(resposta != null) {
			servico.deletar(resposta);
			
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}	
}
