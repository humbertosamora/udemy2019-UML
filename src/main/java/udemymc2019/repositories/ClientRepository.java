package udemymc2019.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemymc2019.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
