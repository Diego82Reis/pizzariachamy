package br.com.PizzariaChamyPizza.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

@Entity
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)   // Para incrementar o id no banco de dados
	private Integer id;

	private String nome;
	
	private String ingredientes;

	private double preco;

	private Integer pedaco;

	private LocalDateTime dataCriacao = LocalDateTime.now();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Integer getPedaco() {
		return pedaco;
	}

	public void setPedaco(Integer pedaco) {
		this.pedaco = pedaco;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	
}
