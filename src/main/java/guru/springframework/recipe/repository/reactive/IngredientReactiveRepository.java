package guru.springframework.recipe.repository.reactive;

import guru.springframework.recipe.domain.Ingredient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * @author Krzysztof Kukla
 */
public interface IngredientReactiveRepository  extends ReactiveMongoRepository<Ingredient, String> {
    Mono<Ingredient> findByRecipeIdAndId(String recipeId, String ingredientId);

}
