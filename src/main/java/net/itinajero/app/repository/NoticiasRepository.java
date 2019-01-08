package net.itinajero.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import net.itinajero.app.model.Noticia;


@Repository
//public interface NoticiasRepository extends CrudRepository<Noticia, Integer> {
public interface NoticiasRepository extends JpaRepository<Noticia, Integer> {
	
//select * from noticia where estatus = ?
List<Noticia> findByEstatus(String estatus);

//select * from noticia where fecha = ?
List<Noticia> findByFecha(Date fecha);

List<Noticia> findByEstatusAndFecha(String estatus, Date fecha);

List<Noticia> findByEstatusOrFecha(String estatus, Date fecha);

List<Noticia> findByFechaBetween(Date fecha1, Date fecha2);

@Query(value="select * from noticias n where n.estatus='Activa' order by id desc limit 3", nativeQuery = true)
List<Noticia> listLastActive();
}
