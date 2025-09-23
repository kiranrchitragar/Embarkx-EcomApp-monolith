package org.Embarkx.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Entity(name = "cart_items")
@Data
public class CartItem extends BaseEntity{

    @ManyToOne // One user can add many cart items
    @JoinColumn(name="user_id",nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    private Integer quantity;
    private BigDecimal price;
}
