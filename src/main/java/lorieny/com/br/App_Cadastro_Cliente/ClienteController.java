package lorieny.com.br.App_Cadastro_Cliente;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("cliente")
public class ClienteController {

    ClienteRepository clienteRepository;
    ClienteMapper clienteMapper;


    private static Logger logger = LoggerFactory.getLogger(AppCadastroClienteApplication.class);


    @GetMapping
    public List<ClienteResponse> buscarTodos(){

        logger.info("Iniciando busca de todos clientes ");

        return clienteMapper.toCollectionModel(clienteRepository.findAll());
    }

    @GetMapping("/buscar_por_cpf/{cpf}")
    public Object buscarPorCpf(@PathVariable String cpf){

        logger.info("Iniciando busca do cliente com numero de cpf: "+ cpf);

    Optional<Cliente> optional = clienteRepository.findByCpf(cpf);
        if (optional.isPresent()) {

            logger.info("Cliente com cpf: "+ cpf +" localizado");

            return  clienteRepository.findByCpf(cpf)
                    .map(cliente -> ResponseEntity.ok(clienteMapper.toModel(cliente)));
        }
        logger.info("Cliente com cpf: "+ cpf +" não localizado");

        return new ResponseEntity<String>("Cliente não localizado na base de dados",HttpStatus.NOT_FOUND);

    }


    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Object cadastrarCliente(@Valid @RequestBody Cliente cliente) {

        logger.info("Iniciando consulta de cadastro do cliente : "+ cliente+"para cadastro");

        Optional<Cliente> optional = clienteRepository.findByCpf(cliente.getCpf());

        if (!optional.isPresent()) {

            logger.info("Iniciando cadastro do cliente : "+ cliente);

            Cliente clienteNovo = clienteRepository.save(cliente);
        return clienteMapper.toModel(clienteNovo);
    }
        logger.info("Cliente já possuí cadastro. Cpf: "+ cliente.getCpf());

        return new ResponseEntity<String>("Cliente já possuí cadastro",HttpStatus.NOT_FOUND);}

    @PutMapping("/alterar-por-cpf")
    @ResponseBody
    public Object alterarDadosCliente(@Valid @RequestBody Cliente cliente) {

        logger.info("Iniciando consulta de cadastro do cliente com numero de id: "+ cliente+" para alteração");

        if (!clienteRepository.existsById(cliente.getId())) {

            logger.info("Cliente não localizado na base de dados. Cpf: "+ cliente);

            return new ResponseEntity<String>("Cliente não localizado na base de dados",HttpStatus.NOT_FOUND);}

        logger.info("Iniciando atualização do cliente no banco de dados. Cpf: "+ cliente);

        Cliente atualCliente = clienteRepository.saveAndFlush(cliente);
    return  clienteMapper.toModel(atualCliente);


    }


    @DeleteMapping("/deletar-por-cpf")
    @Transactional
    @ResponseBody
    public ResponseEntity<String> deletarCliente(@RequestParam String cpf) {

        logger.info("Iniciando consulta de cadastro do cliente com numero de cpf: "+cpf+"para deletar");

        Optional<Cliente> optional = clienteRepository.findByCpf(cpf);
        if (optional.isPresent()) {

            logger.info("Iniciando processo para deletar cliente com cpf: "+cpf);

        clienteRepository.deleteByCpf(cpf);
        return new ResponseEntity<String>("Cliente deletado com sucesso", HttpStatus.OK);
    }
        logger.info("Cliente não localizado na base de dados. Cpf: "+ cpf);

        return new ResponseEntity<String>("Cliente não localizado na base de dados",HttpStatus.NOT_FOUND);
}}