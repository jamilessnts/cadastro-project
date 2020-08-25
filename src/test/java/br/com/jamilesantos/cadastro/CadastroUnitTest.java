package br.com.jamilesantos.cadastro;

import br.com.jamilesantos.cadastro.model.Cliente;
import br.com.jamilesantos.cadastro.repository.ClienteRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;


@RunWith(SpringRunner.class)
@ContextConfiguration()
@DataJpaTest
public class CadastroUnitTest {

    @Autowired
    ClienteRepository repository;
    @Autowired
    private TestEntityManager clienteManager;

    @Test
    public void armazenaCliente() {
        Cliente cliente = repository.save(new Cliente("joao", "20200404", "01512365819","Saude"));

        assertThat(cliente).hasFieldOrPropertyWithValue("nome", "joao");
        assertThat(cliente).hasFieldOrPropertyWithValue("dataNascimento", "20200404");
        assertThat(cliente).hasFieldOrPropertyWithValue("cpf", "01512365819");
        assertThat(cliente).hasFieldOrPropertyWithValue("endereco", "Saude");
    }

    @Test
    public void getAllClientes() {
        Cliente cliente1 = new Cliente("maria", "20190404", "01512365818","centro");
        clienteManager.persist(cliente1);

        Cliente cliente2 = new Cliente("joao", "20190405", "01512365819","centro");
        clienteManager.persist(cliente2);

        Cliente cliente3 = new Cliente("luca", "20190407", "01512365718","centro");
        clienteManager.persist(cliente3);

        List<Cliente> clientes = repository.findAll();

        assertThat(clientes).hasSize(3).contains(cliente1, cliente2, cliente3);
    }

    @Test
    public void getClienteById() {
        Cliente cliente1 = new Cliente("maria", "20190404", "01512365818","centro");
        clienteManager.persist(cliente1);

        Cliente cliente2 = new Cliente("joao", "20190405", "01512365819","centro");
        clienteManager.persist(cliente2);

        Cliente foundCliente = repository.findById(cliente2.getId()).get();

        assertThat(foundCliente).isEqualTo(cliente2);
    }

    @Test
    public void notFindClienteQuandoRepositoryVazio() {
        List<Cliente> clientes = repository.findAll();

        assertThat(clientes).isEmpty();
    }

}
