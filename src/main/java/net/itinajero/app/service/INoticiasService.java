package net.itinajero.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.model.Pelicula;

public interface INoticiasService {
	void guardar(Noticia noticia);
	Page<Noticia> consultaNoticias(Pageable page);
	List<Noticia> consultaUltimasActivas();
	Noticia buscarPorId(int idNoticia);
	void eliminar(int idNoticia);
}
