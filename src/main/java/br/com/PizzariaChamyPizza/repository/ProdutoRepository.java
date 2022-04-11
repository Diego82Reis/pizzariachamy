package br.com.PizzariaChamyPizza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.PizzariaChamyPizza.model.Produto;

	@Repository
	public interface ProdutoRepository extends CrudRepository<Produto, String> {
		Produto findById(Integer id);

		List<Produto> findByNome(String nome);

		// Query para a busca
		@Query(value = "select u from Produto u where u.nome like %?1%")
		List<Produto> findByNomesProduto(String nome);
		}

