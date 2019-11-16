package guru.springframework.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Entity
@Data

//because we have circular reference in Category and Recipe entities we have to avoid in one of them in equalsAndHashCode method
@EqualsAndHashCode(exclude = {"recipes"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    //mappedBy indicates filed in related entity
    //it allows to avoid bi-directional
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;
}
