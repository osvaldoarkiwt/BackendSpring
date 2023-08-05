package br.com.iwt.pizzaria.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iwt.pizzaria.domain.model.Cliente;
import br.com.iwt.pizzaria.domain.service.CadastroClienteService;

@RequestMapping("/clientes")
@RestController
public class ClienteController {

	@Autowired
	CadastroClienteService servico;
	
	@GetMapping
	public List<Cliente> listarClientes(){
		return servico.listarTodos();
	}
	
}
