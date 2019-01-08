package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IDetalleService;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {

	@Autowired
	private IDetalleService serviceDetalles;
	
	@Autowired
	private IPeliculasService servicePelicula;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Pelicula> lista = servicePelicula.buscarTodas();
		model.addAttribute("peliculas", lista);
		return "peliculas/listPeliculas";
	}
	@GetMapping(value="/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Pelicula> lista = servicePelicula.buscarTodas(page);
		model.addAttribute("peliculas", lista);
		return "peliculas/listPeliculas";
	}

	@GetMapping("/create")
	public String crear(@ModelAttribute Pelicula pelicula) {
		return "peliculas/formPelicula";
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {
		
		if (result.hasErrors()) {
			System.out.println("Existen errores");
			return "peliculas/formPelicula";
		}

		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart, request);
			pelicula.setImagen(nombreImagen);
		}
//		for(ObjectError error : result.getAllErrors()) {
//			System.out.println(error.getDefaultMessage());
//		}
		System.out.println("Antes: "+pelicula.getDetalle());
		serviceDetalles.insertar(pelicula.getDetalle());
		System.out.println("Despues: "+pelicula.getDetalle());
		
		servicePelicula.insertar(pelicula);
		System.out.println("Recibiendo obj Pelicula: " + pelicula);
		
		attributes.addFlashAttribute("mensaje", "Registro Guardado");
//		return "peliculas/formPelicula";
		return "redirect:/peliculas/indexPaginate";
	}
	
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable("id") int idPelicula, Model model) {
		Pelicula pelicula = servicePelicula.buscarPorId(idPelicula);
		model.addAttribute("pelicula", pelicula);
		return "peliculas/formPelicula";
	}
	
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable("id") int idPelicula, RedirectAttributes attributes) {
		Pelicula pelicula = servicePelicula.buscarPorId(idPelicula);
		
		//eliminación de la pelicula por el id de pelicula
		servicePelicula.eliminar(idPelicula);
		
		//eliminación del detalle obnteniendo el idDetalle de la referencia al detalle de la pelicula.
		serviceDetalles.eliminar(pelicula.getDetalle().getId());
		attributes.addFlashAttribute("mensaje", "La pelicula fue eliminada");
		return "redirect:/peliculas/indexPaginate";
	}
	
	@ModelAttribute("generos")
	public List<String> getGeneros(){
		return servicePelicula.buscarGeneros();
	}
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
