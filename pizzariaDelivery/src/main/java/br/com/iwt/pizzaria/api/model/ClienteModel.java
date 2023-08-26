package br.com.iwt.pizzaria.api.model;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteModel {
	
	private UUID id;
	private String nome;
	private String endereco;
	private List<UUID> comprasId;
}
