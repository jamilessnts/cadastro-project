package br.com.jamilesantos.cadastro.model;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Classe modelo de Cliente
 * @author Jamile Santos
 *
 */

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "Nome deve ser preenchido")
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotBlank(message = "Data de Nascimento deve ser preenchida")
    private String dataNascimento;

    @Column(name = "cpf", nullable = false, unique = true)
    @NotBlank(message = "CPF deve ser preenchido")
    private String cpf;

    @Column(name = "endereco", nullable = false)
    @NotBlank(message = "Endereco deve ser preenchido")
    private String endereco;

    public Cliente(){

    }

    public Cliente(String nome, String dataNascimento, String cpf, String endereco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
