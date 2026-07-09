package co.istad.sakkda.ecommerceapi.feature.category;

import co.istad.sakkda.ecommerceapi.feature.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(nullable = false, length = 50, unique = true)
        private String name;

        @Column(columnDefinition = "TEXT")
        private String description;

        @Column(length = 100)
        private String icon;

        @OneToMany(mappedBy = "category")
        private List<Product> products;
}
