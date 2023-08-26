package br.com.iwt.pizzaria.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iwt.pizzaria.domain.model.Compra;

public interface ComprasRepository extends JpaRepository<Compra, UUID>{
	
	public List<Compra> findCompraByClienteId(UUID clienteId);
}
