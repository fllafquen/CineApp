package net.itinajero.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.INoticiasService;
import net.itinajero.app.service.IPeliculasService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {
	
	@Autowired
	private IPeliculasService servicePelicula;
	
	@Autowired
	private INoticiasService serviceNoticias;
	
	@GetMapping(value="/create")
	public String crear(@ModelAttribute Noticia noticia) {
		return "noticias/formNoticia";
	}
	
	@PostMapping(value="/save")
	public String guardar(@ModelAttribute Noticia noticia, BindingResult result, RedirectAttributes attributes) {
		//pendiente para guardar el objeto noticia en la base de datos.
		if (result.hasErrors()) {
			System.out.println("Existen errores");
			return "noticias/formNoticia";
		}
		serviceNoticias.guardar(noticia);
		attributes.addFlashAttribute("mensaje", "Registro Guardado");
		return "redirect:/noticias/indexPaginate";
	}
	
	@GetMapping(value="/indexPaginate")
	public String mostrarIndex(Model model, Pageable page) {
		Page<Noticia> noticias = serviceNoticias.consultaNoticias(page);
		model.addAttribute("noticias", noticias);
		return "noticias/listNoticias";
	}
	
	
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable("id") int idNoticia, Model model) {
		Noticia noticia = serviceNoticias.buscarPorId(idNoticia);
		model.addAttribute("noticia", noticia);
		return "noticias/formNoticia";
	}
	
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable("id") int idNoticia, RedirectAttributes attributes) {
		Noticia noticia = serviceNoticias.buscarPorId(idNoticia);
		
		//eliminaci�n de la pelicula por el id de pelicula
		serviceNoticias.eliminar(idNoticia);
		attributes.addFlashAttribute("mensaje", "La noticia fue eliminada");
		return "redirect:/noticias/indexPaginate";
	}
}
