package udemymc2019.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemymc2019.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
