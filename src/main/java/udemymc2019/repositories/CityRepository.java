package udemymc2019.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemymc2019.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
