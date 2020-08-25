package br.com.jamilesantos.cadastro.controllers;
import br.com.jamilesantos.cadastro.exception.CustomErrorResponse;
import br.com.jamilesantos.cadastro.exception.CustomExceptionHandler;
import br.com.jamilesantos.cadastro.exception.CustomNotFoundException;
import br.com.jamilesantos.cadastro.model.Cliente;
import br.com.jamilesantos.cadastro.repository.ClienteRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.WebRequest;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Controller - Cadastro de clientes
 * @author Jamile Santos
 *
 */

@RestController
@RequestMapping("/api/v1/cadastro")

public class CadastroController {
    @Autowired
    ClienteRepository clienteRepository;

    /* Consulta a lista de todos os clientes do cadastro, se a lista está vazia é retornada resposta sem conteúdo */

    @GetMapping("/clientes")
    public ResponseEntity<?> getAllClientes() throws CustomNotFoundException {
        if (clienteRepository.findAll().isEmpty()) {
            return new ResponseEntity<>(new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    new Date(), "Não há clientes cadastrados","Cliente não há clientes na base"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(clienteRepository.findAll(), HttpStatus.OK);
    }

    /* Consulta cliente do cadastro por id */

    @GetMapping("/lerCliente/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") long id) {

            Optional<Cliente> clienteInfo = clienteRepository.findById(id);
            if(clienteInfo.isPresent()){
                return new ResponseEntity<>(clienteInfo.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        new Date(), "Cliente ID: ["+id+ "] não encontrado ","Cliente não encontrado"),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    /* Criar cliente no cadastro */

    @PostMapping("/novoCliente")
    public ResponseEntity<?> createCliente(@Valid @RequestBody Cliente cliente) {
        try {
            Cliente clienteInfo = clienteRepository
                    .save(new Cliente(cliente.getNome(), cliente.getDataNascimento(), cliente.getCpf(),
                            cliente.getEndereco()));
            return new ResponseEntity<>(clienteInfo, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    new Date(), "Internal Error","Cliente não criado"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /* Excluir cliente do cadastro a partir do ID */

    @DeleteMapping("/removeCliente/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable("id") long id) {
        try {
            clienteRepository.deleteById(id);
            return new ResponseEntity<>("Cliente ID: "+id+" excluído com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    new Date(), "Internal Error","Cliente não excluído, Id incorreto ou inexistente"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /* Atualizar cadastro a partir do ID do cliente */

    @PutMapping("/atualizarCliente/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable("id") long id, @Valid @RequestBody Cliente cliente) {
        Optional<Cliente> infoCliente = clienteRepository.findById(id);

        if (infoCliente.isPresent()) {
            Cliente _infoCliente = infoCliente.get();
            _infoCliente.setNome(cliente.getNome());
            _infoCliente.setDataNascimento(cliente.getDataNascimento());
            _infoCliente.setCpf(cliente.getCpf());
            _infoCliente.setEndereco(cliente.getEndereco());
            clienteRepository.save(_infoCliente);
            return new ResponseEntity<>("Cliente ID: ["+id+"] atualizado com sucesso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    new Date(), "Cliente ID: ["+id+"] não encontrado","Cliente não atualizado"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

