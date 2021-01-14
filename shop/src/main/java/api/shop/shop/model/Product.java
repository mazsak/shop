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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    @OneToOne
    private Subdirectory subdirectory;
    private double price;
    private int stockAmount;
}
