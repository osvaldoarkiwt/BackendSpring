package br.com.iwt.pizzaria.api.model;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteModel {
	
	private UUID id;
	private String nome;
	private String endereco;
	private List<UUID> comprasId;
}
