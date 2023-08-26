package br.com.iwt.pizzaria.domain.repository.spec;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import br.com.iwt.pizzaria.domain.model.Cliente;
import br.com.iwt.pizzaria.domain.model.filter.ClienteFilter;
import jakarta.persistence.criteria.Predicate;

public class ClienteSpec {

	public static Specification<Cliente> usandoFiltro(ClienteFilter filtro){
		
		return (root,query,builder) ->{
			var predicates = new ArrayList<Predicate>();

	         if(filtro.getNome() != null) {
	             predicates.add(builder.like(builder.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
	         }

	         if(filtro.getEndereco() != null) {
	             predicates.add(builder.like(builder.lower(root.get("endereco")), "%" + filtro.getEndereco().toLowerCase() + "%"));
	         }
	         /*
	         if(filtro.getVlrrendaMedia() != null) {
	             predicates.add(builder.equal(root.get("vlrrendaMedia"),  filtro.getVlrrendaMedia()));
	         }*/
	         
			return builder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
		};
	}
}