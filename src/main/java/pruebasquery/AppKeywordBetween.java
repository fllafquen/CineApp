package pruebasquery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppKeywordBetween {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<Noticia> lista;
		try {
			lista = repo.findByFechaBetween(format.parse("2018-12-07"), format.parse("2018-12-09"));
			
			for(Noticia n: lista){
				System.out.println(n);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		context.close();
	}

}
