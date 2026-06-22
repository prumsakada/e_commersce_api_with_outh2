package co.istad.sakkda.ecommerceapi.feature.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
        extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
