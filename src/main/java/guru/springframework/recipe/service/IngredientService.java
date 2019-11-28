package guru.springframework.recipe.service;

import guru.springframework.recipe.commands.IngredientCommand;

/**
 * @author Krzysztof Kukla
 */
public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteIngredientById(Long recipeId, Long ingredientId);

}
