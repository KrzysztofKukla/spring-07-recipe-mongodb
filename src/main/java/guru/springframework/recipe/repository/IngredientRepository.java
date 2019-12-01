package guru.springframework.recipe.repository;

import guru.springframework.recipe.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByRecipeIdAndId(Long recipeId, Long id);

}
