package pruebasjparepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppPaging {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
//		Page<Noticia> page = repo.findAll(PageRequest.of(1, 2));
		
		Page<Noticia> page = repo.findAll(PageRequest.of(0, 4, Sort.by("titulo")));
		for(Noticia n : page) {
			System.out.println(n);
		}
		context.close();
	}

}
