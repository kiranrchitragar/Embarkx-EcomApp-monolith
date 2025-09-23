package org.Embarkx.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name="order_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
    private Integer quantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;


}
