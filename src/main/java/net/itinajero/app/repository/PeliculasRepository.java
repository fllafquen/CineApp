package net.itinajero.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.itinajero.app.model.Pelicula;

@Repository
public interface PeliculasRepository extends JpaRepository<Pelicula, Integer> {
@Query("Select p from Pelicula p where p.titulo like %?1%")
List<Pelicula> anyMethod(String titulo);
// Listado de peliculas filtradas por estatus
public List<Pelicula> findByEstatus_OrderByTitulo(String estatus);
}
