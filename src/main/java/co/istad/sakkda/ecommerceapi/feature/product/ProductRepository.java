package co.istad.sakkda.ecommerceapi.feature.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, String> {
}
