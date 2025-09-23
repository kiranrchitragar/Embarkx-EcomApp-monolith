package org.Embarkx.repository;

import org.Embarkx.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByActiveTrue();

    @Query("SELECT p FROM product p WHERE p.active=true AND p.stockQuantity > 0 " +
            "AND LOWER(p.name) LIKE (CONCAT('%',:keyword,'%'))")
    List<Product> searchProducts(String keyword);
}
