package guru.springframework.recipe.service;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Recipe;

import java.util.Collection;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
public interface RecipeService {
    Recipe findById(String id);

    Set<Recipe> findAll();

    void saveAll(Collection<Recipe> recipes);

    RecipeCommand findRecipeCommandById(String id);

    Recipe saveRecipe(Recipe recipe);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    void deleteById(String id);

}
