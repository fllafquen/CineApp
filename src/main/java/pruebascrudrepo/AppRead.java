package pruebascrudrepo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppRead {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
//		Optional<Noticia> noticia = repo.findById(1);
//		System.out.println(noticia);
		
		List<Integer> ids =new  LinkedList<Integer>();
		ids.add(5);
		ids.add(6);
		
		Iterable<Noticia> it = repo.findAllById(ids);
		for(Noticia n : it) {
			System.out.println(n);
		}
		context.close();
	}

}
