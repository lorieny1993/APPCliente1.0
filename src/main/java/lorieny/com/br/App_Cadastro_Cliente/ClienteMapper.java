package lorieny.com.br.App_Cadastro_Cliente;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClienteMapper {
    private ModelMapper modelMapper;

    public ClienteResponse toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteResponse.class);
    }

    public List<ClienteResponse> toCollectionModel(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
