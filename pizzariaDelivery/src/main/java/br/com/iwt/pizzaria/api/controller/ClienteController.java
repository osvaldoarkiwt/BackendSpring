package br.com.iwt.pizzaria.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import br.com.iwt.pizzaria.api.model.ClienteModel;
import br.com.iwt.pizzaria.api.model.input.ClienteInput;
import br.com.iwt.pizzaria.domain.model.filter.ClienteFilter;
import br.com.iwt.pizzaria.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	CadastroClienteService servico;
	
	@GetMapping
	public ResponseEntity<Page<ClienteModel>> listarClientes(ClienteFilter filtro,@PageableDefault(size=2) Pageable pageable){		
		
		return ResponseEntity.ok(servico.listarTodos(filtro,pageable));
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteModel> listarCliente(@PathVariable UUID clienteId) {
		
		return ResponseEntity.ok(servico.listarPorIdOrThrow(clienteId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ClienteModel adicionarCliente(@RequestBody ClienteInput clienteInput) {
		
		return servico.adicionar(clienteInput);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<ClienteModel> atualizaCliente(@PathVariable UUID clienteId, @RequestBody ClienteInput clienteInput) {
		
			return ResponseEntity.ok(servico.atualizarCliente(clienteId, clienteInput));
	}
	
	@DeleteMapping("/{clienteId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletaCliente(@PathVariable UUID clienteId){
	
		servico.deletar(clienteId);
	}	
}
