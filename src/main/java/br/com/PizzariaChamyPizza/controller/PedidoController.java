package br.com.PizzariaChamyPizza.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.PizzariaChamyPizza.model.Pedido;
import br.com.PizzariaChamyPizza.repository.PedidoRepository;

@Controller
public class PedidoController {

	@Autowired
	private PedidoRepository pr;

	// GET que chama o FORM que cadastra pedido
	@RequestMapping(value = "/cadastraPedido", method = RequestMethod.GET) // mexi
	public String form() {
		return "pedido/formPedido";
	}

	// POST que cadastra o pedido
	@RequestMapping(value = "/cadastraPedido", method = RequestMethod.POST)
	public String form(@Valid Pedido pedido, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos...");
			return "redirect:/cadastraPedido";
		}

		pr.save(pedido);
		attributes.addFlashAttribute("mensagem", "Pedido gerado com sucesso!");
		return "redirect:/cadastraPedido";
	}

	// GET que lista os pedidos
	@RequestMapping("/pedidos")
	public ModelAndView listaPedidos() {
		ModelAndView mv = new ModelAndView("pedido/listaPedido");
		Iterable<Pedido> pedidos = pr.findAll();
		mv.addObject("pedidos", pedidos);
		return mv;
	}

	// GET que mostra os detalhes do pedido
	@RequestMapping("/pedido/{id}")
	public ModelAndView detalhesPedido(@PathVariable("id") Integer id) {
		Pedido pedido = pr.findById(id);
		ModelAndView mv = new ModelAndView("pedido/detalhesPedido");
		mv.addObject("pedido", pedido);
		return mv;
	}

	// GET que deleta o pedido
	@RequestMapping("/deletarPedido")
	public String deletarPedido(Integer id) {
		Pedido pedido = pr.findById(id);
		pr.delete(pedido);
		return "redirect:/pedidos";
	}

	// Métodos que atualizam pedido
	// GET que chama o formulário de edição do pedido
	@RequestMapping("/editarPedido")
	public ModelAndView editarPedido(Integer id) {
		Pedido pedido = pr.findById(id);
		ModelAndView mv = new ModelAndView("pedido/updatePedido");
		mv.addObject("pedido", pedido);
		return mv;
	}

	// POST do FORM que atualiza o pedido
	@RequestMapping(value = "/editarPedido", method = RequestMethod.POST)
	public String updatePedido(@Valid Pedido pedido, BindingResult result, RedirectAttributes attributes) {

		pr.save(pedido);
		attributes.addFlashAttribute("success", "Pedido alterado com sucesso!");

		Integer idLong = pedido.getId();
		String id = "" + idLong;
		return "redirect:/pedido/" + id;
	}

}
