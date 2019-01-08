package net.itinajero.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.itinajero.app.model.Banner;
import net.itinajero.app.model.Noticia;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {
	@Query("select b from Banner b where b.estatus = 'Activo'")
	List<Banner> bannerActivo();
}
