package org.Embarkx.repository;

import org.Embarkx.entities.CartItem;
import org.Embarkx.entities.Product;
import org.Embarkx.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    CartItem findByUsersAndProduct(Users users, Product product);

    void deleteByUsersAndProduct(Users user, Product product);

    List<CartItem> findByUsers(Users users);

    void deleteByUsers(Users users);
}
