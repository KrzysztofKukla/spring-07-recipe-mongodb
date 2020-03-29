package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.converter.RecipeCommandToRecipe;
import guru.springframework.recipe.converter.RecipeToRecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repository.reactive.RecipeReactiveRepository;
import guru.springframework.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeServiceImpl implements RecipeService {
    private final RecipeReactiveRepository recipeReactiveRepository;

    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    @Override
    public Mono<Recipe> findById(final String id) {
        return getRecipe(id);
    }

    @Override
    public Mono<RecipeCommand> findRecipeCommandById(String id) {
        Mono<Recipe> recipeMono = getRecipe(id);
        return recipeMono.map(i -> recipeToRecipeCommand.convert(i));
    }

    @Override
    public Flux<Recipe> findAll() {
        return recipeReactiveRepository.findAll();
    }

    @Override
    public Flux<Recipe> saveAll(final Collection<Recipe> recipes) {
        return recipeReactiveRepository.saveAll(recipes);
    }

    @Override
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand recipeCommand) {
        log.debug("Saved recipeId ");
        return recipeReactiveRepository.save(recipeCommandToRecipe.convert(recipeCommand))
            .map(i->recipeToRecipeCommand.convert(i));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        recipeReactiveRepository.deleteById(id);
        log.debug("Recipe has been deleted");
        return Mono.empty();
    }

    @Override
    public Mono<Recipe> saveRecipe(Recipe recipe) {
        return recipeReactiveRepository.save(recipe);
    }

    private Mono<Recipe> getRecipe(String id) {
        log.debug("find recipe by id-> " + id);
        return recipeReactiveRepository.findById(id);
    }

}
