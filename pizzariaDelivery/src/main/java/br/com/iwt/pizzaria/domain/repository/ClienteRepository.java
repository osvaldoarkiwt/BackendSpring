package br.com.iwt.pizzaria.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.iwt.pizzaria.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID>,JpaSpecificationExecutor<Cliente>  {
	public Cliente findByNome(String nome);
}
