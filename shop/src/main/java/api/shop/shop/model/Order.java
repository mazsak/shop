package api.shop.shop.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String orderNumber;
    @OneToMany
    private List<Product> items;
    private double totalPrice;
}
