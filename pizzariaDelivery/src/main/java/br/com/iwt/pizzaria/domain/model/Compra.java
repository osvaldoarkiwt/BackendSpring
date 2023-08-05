package br.com.iwt.pizzaria.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="compras")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "compra_id", updatable = false, unique = true, nullable = false)
	@EqualsAndHashCode.Include
	private UUID id;
	
	@ManyToMany
	private List<ItemCompra> items;
	
	@ManyToOne
	private Cliente cliente;
	
	private BigDecimal total;
	
	public void calculaTotal() {
		
			this.total = this.items.stream()
				.map(item -> item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade())))
				.reduce(BigDecimal.ZERO,BigDecimal::add);
				//.reduce(BigDecimal.ZERO, (soma, item)-> soma.add(item));
	}
	
}