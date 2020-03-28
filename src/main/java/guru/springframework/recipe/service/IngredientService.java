package guru.springframework.recipe.service;

import guru.springframework.recipe.commands.IngredientCommand;
import reactor.core.publisher.Mono;

/**
 * @author Krzysztof Kukla
 */
public interface IngredientService {
    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand ingredientCommand);

    Mono<Void> deleteIngredientById(String recipeId, String ingredientId);

}
