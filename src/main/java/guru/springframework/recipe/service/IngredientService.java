package guru.springframework.recipe.service;

import guru.springframework.recipe.commands.IngredientCommand;

/**
 * @author Krzysztof Kukla
 */
public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteIngredientById(String recipeId, String ingredientId);

}
