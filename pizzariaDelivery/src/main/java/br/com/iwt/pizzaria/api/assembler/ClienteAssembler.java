package br.com.iwt.pizzaria.api.assembler;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.iwt.pizzaria.api.model.ClienteModel;
import br.com.iwt.pizzaria.api.model.input.ClienteInput;
import br.com.iwt.pizzaria.domain.model.Cliente;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClienteAssembler {

	private ModelMapper modelMapper;
	
	public ClienteModel toModel(Cliente cliente) {
		
		ClienteModel clienteModel = modelMapper.map(cliente, ClienteModel.class);
		
		List<UUID> comprasIds = cliente.getCompras().stream()
							.map(compra -> compra.getId())
							.collect(Collectors.toList());
		
		clienteModel.setComprasId(comprasIds);
		
		return clienteModel;
	}
	
	public List<ClienteModel> toCollectionModel(List<Cliente> clientes){
		return clientes.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Cliente toEntity(ClienteInput clienteInput) {
        return modelMapper.map(clienteInput, Cliente.class);
    }
}