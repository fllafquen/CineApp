package net.itinajero.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.itinajero.app.model.Banner;
import net.itinajero.app.model.Noticia;

public interface IBannersService {
	void insertar(Banner banner);
	List<Banner> buscarTodos();
	List<Banner> buscarActivos();
	void guardar(Banner banner);
	void eliminar(int IdBanner);
	Banner buscarPorId(int IdBanner);
	Page<Banner> buscarTodosPage(Pageable page);
}
