package guru.springframework.recipe.repository;

import guru.springframework.recipe.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Krzysztof Kukla
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByRecipeIdAndId(Long recipeId, Long id);

}
