package pruebasrelaciones;

import java.text.ParseException;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.repository.PeliculasRepository;

public class AppFindAll {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		PeliculasRepository repo = context.getBean("peliculasRepository", PeliculasRepository.class);
		List<Pelicula> lista = repo.findAll();
//		Pelicula lista = repo.anyMethod("Rangers");
//		lista = repo.anyMethod("Rangers");
//		for(Pelicula p: lista){
//			System.out.println(p);
//		}
		for (Pelicula p : lista) {
			System.out.println(p);
		}
		context.close();
	}

}
