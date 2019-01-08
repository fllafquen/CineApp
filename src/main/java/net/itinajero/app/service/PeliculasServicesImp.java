package net.itinajero.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Pelicula;

//@Service
public class PeliculasServicesImp implements IPeliculasService{
	
	private List<Pelicula> lista = null;
	
	public PeliculasServicesImp() {
		System.out.println("Creando una instancia de la clase peliculaSerivceImp");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			lista = new LinkedList<>();

			Pelicula pelicula1 = new Pelicula();
			pelicula1.setId(1);
			pelicula1.setTitulo("Power rangers");
			pelicula1.setDuracion(120);
			pelicula1.setClasificacion("B");
			pelicula1.setGenero("Aventura");
			pelicula1.setFechaEstreno(formatter.parse("02-05-2017"));
			pelicula1.setImagen("power.png");
			// estatus="Activa"

			Pelicula pelicula2 = new Pelicula();
			pelicula2.setId(2);
			pelicula2.setTitulo("Los Vengadores");
			pelicula2.setDuracion(132);
			pelicula2.setClasificacion("A");
			pelicula2.setGenero("Acción");
			pelicula2.setFechaEstreno(formatter.parse("20-05-2018"));
			pelicula2.setImagen("vengadores.png");
			// estatus="Activa"

			Pelicula pelicula3 = new Pelicula();
			pelicula3.setId(3);
			pelicula3.setTitulo("Thor");
			pelicula3.setDuracion(106);
			pelicula3.setClasificacion("B");
			pelicula3.setGenero("Acción");
			pelicula3.setFechaEstreno(formatter.parse("28-05-2018"));
			pelicula3.setImagen("thor.png");
			pelicula3.setEstatus("Inactiva");

			Pelicula pelicula4 = new Pelicula();
			pelicula4.setId(4);
			pelicula4.setTitulo("Avengers: Age of Ultron");
			pelicula4.setDuracion(106);
			pelicula4.setClasificacion("B");
			pelicula4.setGenero("Acción");
			pelicula4.setFechaEstreno(formatter.parse("28-05-2015"));
			pelicula4.setImagen("Age_Of_Ultron.png");
			pelicula4.setEstatus("Inactiva");

			Pelicula pelicula5 = new Pelicula();
			pelicula5.setId(5);
			pelicula5.setTitulo("Capitán América: el primer vengador");
			pelicula5.setDuracion(106);
			pelicula5.setClasificacion("B");
			pelicula5.setGenero("Acción");
			pelicula5.setFechaEstreno(formatter.parse("28-05-2011"));
			pelicula5.setImagen("CaptainAmerica.png");
			pelicula5.setEstatus("Inactiva");

			// Agregamos los objetos Pelicula a la lista
			lista.add(pelicula1);
			lista.add(pelicula2);
			lista.add(pelicula3);
			lista.add(pelicula4);
			lista.add(pelicula5);
			} catch(ParseException e) {
				System.out.println("Error: " +e.getMessage());
			}
		}
	
	@Override
	public List<Pelicula> buscarTodas() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public Pelicula buscarPorId(int idPelicula) {
		for(Pelicula p: lista) {
			if(p.getId() == idPelicula) {
				return p;
			}
		}
		return null;
	}

	@Override
	public void insertar(Pelicula pelicula) {
		lista.add(pelicula);
	}

	@Override
	public List<String> buscarGeneros() {
		
		//esta lista podría ser genera desde una base de datos.
			List<String> generos = new LinkedList<>();
			generos.add("Accion");
			generos.add("Aventura");
			generos.add("Clasicas");
			generos.add("Comedia Romantica");
			generos.add("Drama");
			generos.add("Terror");
			generos.add("Infantil");
			generos.add("Accion y Aventura");
			generos.add("Romantica");
			return generos;
	}

	@Override
	public void eliminar(int idPelicula) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Pelicula> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pelicula> buscarActivas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pelicula> buscarPorFecha(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

}
