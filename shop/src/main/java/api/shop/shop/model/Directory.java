package api.shop.shop.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Directory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany
    private List<Subdirectory> subdirectories;

    public void addSubdirectory(Subdirectory subdirectory){
        if (subdirectories == null)
            subdirectories = new ArrayList<>();
        subdirectories.add(subdirectory);
    }
}
