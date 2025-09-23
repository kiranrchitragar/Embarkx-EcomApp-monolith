package org.Embarkx.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
@Data
@NoArgsConstructor
public class Order extends BaseEntity{

    @ManyToOne // One user->Many orders
    @JoinColumn(name = "user_id",nullable = false)
    private Users users;
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    // 1 order -> many order items || cascading all the operations to OrderItems
    private List<OrderItem> items = new ArrayList<>();
}
