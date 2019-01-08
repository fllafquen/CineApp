package net.itinajero.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.itinajero.app.model.Noticia;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.repository.NoticiasRepository;

@Service
public class NoticiasServicesImp implements INoticiasService{

	@Autowired
	NoticiasRepository noticiasRepo;
	
	public NoticiasServicesImp()
	{
		System.out.println("Creando una instancia de la clase NoticiasServiceImpl");
	}
	@Override
	public void guardar(Noticia noticia) {
		noticiasRepo.save(noticia);
	}
	@Override
	public List<Noticia> consultaUltimasActivas() {
		return noticiasRepo.listLastActive();
	}
	@Override
	public Page<Noticia> consultaNoticias(Pageable page) {
		return noticiasRepo.findAll(page);
	}
	@Override
	public Noticia buscarPorId(int idNoticia) {
		Optional<Noticia> opcional =  noticiasRepo.findById(idNoticia);
		if(opcional.isPresent()) {
			return opcional.get();
		}
		return null;
	}
	@Override
	public void eliminar(int idNoticia) {
		noticiasRepo.deleteById(idNoticia);
	}

}
