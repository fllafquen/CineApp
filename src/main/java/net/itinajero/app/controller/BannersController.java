package net.itinajero.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.itinajero.app.model.Banner;
import net.itinajero.app.model.Noticia;
import net.itinajero.app.service.IBannersService;
import net.itinajero.app.util.Utileria;

@Controller
@RequestMapping("/banners/")
public class BannersController {

	@Autowired
	private IBannersService serviceBanners;
		
	/**
	 * Metodo para mostrar el listado de banners
	 * @param model
	 * @return
	 */
	@GetMapping(value="/indexPaginate")
	public String mostrarIndex(Model model, Pageable page) {
		
		Page<Banner> banners = serviceBanners.buscarTodosPage(page);
		model.addAttribute("banners", banners);
		return "banners/listBanners";
	}
	
	/**
	 * Metodo para mostrar el formulario para crear un nuevo Banner
	 * @return
	 */
	@GetMapping("/create")
	public String crear(@ModelAttribute Banner banner) {
		return "banners/formBanner";
		
	}
	
	/**
	 * Metodo para guardar el objeto de modelo de tipo Banner
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(@ModelAttribute Banner banner, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {
		
		if (result.hasErrors()) {
			System.out.println("Existen errores");
			return "banners/formBanner";
		}

		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart, request);
			banner.setArchivo(nombreImagen);
		}
		serviceBanners.guardar(banner);
		// Ejercicio: Implementar el metodo.
		attributes.addFlashAttribute("mensaje", "Registro Guardado");
		return "redirect:/banners/indexPaginate";
	}
	
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable("id") int idBanner, Model model) {
		Banner banner = serviceBanners.buscarPorId(idBanner);
		model.addAttribute("banner", banner);
		return "banners/formBanner";
	}
	
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable("id") int idBanner, RedirectAttributes attributes) {
		Banner banner = serviceBanners.buscarPorId(idBanner);
		
		//eliminación de la pelicula por el id de pelicula
		serviceBanners.eliminar(idBanner);
		attributes.addFlashAttribute("mensaje", "El banner fue eliminada");
		return "redirect:/banners/indexPaginate";
	}
}
