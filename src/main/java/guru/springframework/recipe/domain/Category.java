package guru.springframework.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

//because we have circular reference in Category and Recipe entities we have to avoid in one of them in equalsAndHashCode method
@EqualsAndHashCode(exclude = {"recipes"})
public class Category {
    private String id;
    private String description;
    private Set<Recipe> recipes;
}
