package guru.springframework.recipe.repository;

import guru.springframework.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Krzysztof Kukla
 */
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
