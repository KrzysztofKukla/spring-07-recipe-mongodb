package guru.springframework.recipe.repository;

import guru.springframework.recipe.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    Optional<Ingredient> findByRecipeIdAndId(String recipeId, String id);

}
