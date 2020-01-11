package udemymc2019.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemymc2019.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
