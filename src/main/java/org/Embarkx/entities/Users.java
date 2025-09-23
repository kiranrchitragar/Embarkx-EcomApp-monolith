package org.Embarkx.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity{

    private String firstname;
    private String lastname;
    private String email;
    private String phone;

    @Enumerated(EnumType.STRING) // Store enum values as string, else it will store as 0 or 1
    private UserRole userrole = UserRole.CUSTOMER;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
