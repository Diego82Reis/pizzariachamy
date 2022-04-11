package br.com.PizzariaChamyPizza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.PizzariaChamyPizza.model.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, String> {
	Pedido findById(Integer id);
	List<Pedido> findByCliente(String cliente);
	
	
	@Query(value = "select u from Pedido u where u.cliente like %?1%")
	List<Pedido> findByNomesProduto(String cliente);
	
	
}
