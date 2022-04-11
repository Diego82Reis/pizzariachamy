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

import br.com.PizzariaChamyPizza.model.Produto;
import br.com.PizzariaChamyPizza.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	@Autowired
	private ProdutoRepository pr;
	
	
	
// GET que chama o FORM que cadastra produto
	@RequestMapping(value = "/cadastrarProduto", method = RequestMethod.GET)
	public String form() {
		return "produto/formProduto";
	}

// POST que cadastra o produto
	@RequestMapping(value = "/cadastrarProduto", method = RequestMethod.POST)
	public String form(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos...");
			return "redirect:/cadastrarProduto";
		}

		pr.save(produto);
		attributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso!");
		return "redirect:/cadastrarProduto";
	}

// GET que lista os produtos
	@RequestMapping("/produtos")
	public ModelAndView listaProdutos() {
		ModelAndView mv = new ModelAndView("produto/listaProduto");
		Iterable<Produto> produtos = pr.findAll();
		mv.addObject("produtos", produtos);
		return mv;
	}
	
	
// GET que mostra os detalhes do produto
	@RequestMapping("/produto/{id}")
	public ModelAndView detalhesProduto(@PathVariable("id") Integer id) {
		Produto produto = pr.findById(id);
		ModelAndView mv = new ModelAndView("produto/detalhesProduto");
		mv.addObject("produto", produto);
		return mv;
	}

		
	//Rota cardápio CLiente
			@RequestMapping("/cardapioCliente")
			public ModelAndView abrirCardapio() {
				ModelAndView mv = new ModelAndView("produto/cardapioView");
				Iterable<Produto> produtos = pr.findAll();
				mv.addObject("produtos", produtos);
				return mv;
	}
			
			
// GET que deleta o produto
	@RequestMapping("/deletarProduto")
	public String deletarProduto(Integer id) {
		Produto produto = pr.findById(id);
		pr.delete(produto);
		return "redirect:/produtos";
	}


// Métodos que atualizam produto
// GET que chama o formulário de edição do produto
	@RequestMapping("/editarProduto")
	public ModelAndView editarProduto(Integer id) {
		Produto produto = pr.findById(id);
		ModelAndView mv = new ModelAndView("produto/updateProduto");
		mv.addObject("produto", produto);
		return mv;
	}

// POST do FORM que atualiza o produto
	@RequestMapping(value = "/editarProduto", method = RequestMethod.POST)
	public String updateProduto(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {
		
		pr.save(produto);
		attributes.addFlashAttribute("success", "Produto alterado com sucesso!");

		Integer idLong = produto.getId();
		String id = "" + idLong;
		return "redirect:/produtos/"; 
		
	}
}