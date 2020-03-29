package guru.springframework.recipe.service;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * @author Krzysztof Kukla
 */
public interface RecipeService {
    Mono<Recipe> findById(String id);

    Flux<Recipe> findAll();

    Flux<Recipe> saveAll(Collection<Recipe> recipes);

    Mono<RecipeCommand> findRecipeCommandById(String id);

    Mono<Recipe> saveRecipe(Recipe recipe);

    Mono<RecipeCommand> saveRecipeCommand(RecipeCommand recipeCommand);

    Mono<Void> deleteById(String id);

}
