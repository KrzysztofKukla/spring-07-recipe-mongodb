package guru.springframework.recipe.service;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.web.model.RecipeDto;
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

    Mono<RecipeDto> findRecipeCommandById(String id);

    Mono<Recipe> saveRecipe(Recipe recipe);

    Mono<RecipeDto> saveRecipeCommand(RecipeDto recipeDto);

    Mono<Void> deleteById(String id);

}
