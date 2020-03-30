package guru.springframework.recipe.web.model;

import guru.springframework.recipe.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
public class CategoryDto {
    private String id;
    private String description;
    private List<Recipe> recipes = new ArrayList<>();

}
