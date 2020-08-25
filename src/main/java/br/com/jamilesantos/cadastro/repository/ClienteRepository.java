package br.com.jamilesantos.cadastro.repository;

import br.com.jamilesantos.cadastro.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
