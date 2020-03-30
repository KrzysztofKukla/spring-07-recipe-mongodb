package guru.springframework.recipe.service;

import guru.springframework.recipe.web.model.IngredientDto;
import reactor.core.publisher.Mono;

/**
 * @author Krzysztof Kukla
 */
public interface IngredientService {
    Mono<IngredientDto> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<IngredientDto> saveIngredientCommand(IngredientDto ingredientDto);

    Mono<Void> deleteIngredientById(String recipeId, String ingredientId);

}
