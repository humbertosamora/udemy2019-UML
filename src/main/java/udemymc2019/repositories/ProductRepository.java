package udemymc2019.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemymc2019.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
