package guru.springframework.recipe.commands;

import guru.springframework.recipe.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * from experience I recommend to create pojos class for domain objects,
 * because as you project grows very often you dont need all properties from domain object on UI
 * your properties in domain objects are going to be different than what you are exposing on UI
 *
 * @author Krzysztof Kukla
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String description;
    private Set<Recipe> recipes = new HashSet<>();

}
