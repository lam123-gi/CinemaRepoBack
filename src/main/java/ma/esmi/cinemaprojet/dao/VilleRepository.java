package ma.esmi.cinemaprojet.dao;

import ma.esmi.cinemaprojet.entities.Cinema;
import ma.esmi.cinemaprojet.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface VilleRepository extends JpaRepository<Ville, Long> {
}
