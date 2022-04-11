package br.com.PizzariaChamyPizza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class indexController {
	
	@RequestMapping("/")
	public ModelAndView abrirIndex() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	@RequestMapping("/quemSomos")
	public ModelAndView abrirQuemSomos() {
		ModelAndView mv = new ModelAndView("quem-somos");
		return mv;
	}

}
