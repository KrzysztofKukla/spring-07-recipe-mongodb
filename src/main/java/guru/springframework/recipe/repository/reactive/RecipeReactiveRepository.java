package guru.springframework.recipe.repository.reactive;

import guru.springframework.recipe.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Krzysztof Kukla
 */
public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
