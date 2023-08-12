package br.com.iwt.pizzaria.api.model.input;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {
	
	private UUID id;
	private String nome;
	private String endereco;
}