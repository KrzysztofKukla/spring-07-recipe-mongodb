package guru.springframework.recipe.repository;

import guru.springframework.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Krzysztof Kukla
 */
public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
