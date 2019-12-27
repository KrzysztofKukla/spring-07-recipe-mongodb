package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.converter.RecipeCommandToRecipe;
import guru.springframework.recipe.converter.RecipeToRecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.exception.NotFoundException;
import guru.springframework.recipe.repository.RecipeRepository;
import guru.springframework.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final RecipeRepository recipeRepository;

    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    @Override
    public Recipe findById(final String id) {
        return getRecipe(id);
    }

    //Transactional is required because we are doing conversion outside the scope
    //so if we do lazy loaded properties we'll receive LazyInitializationException
    @Transactional
    @Override
    public RecipeCommand findRecipeCommandById(String id) {
        return recipeToRecipeCommand.convert(getRecipe(id));
    }

    @Override
    public Set<Recipe> findAll() {
        Set<Recipe> all = new HashSet<>();
        recipeRepository.findAll().forEach(all::add);
        return all;
    }

    @Override
    public void saveAll(final Collection<Recipe> recipes) {
        recipeRepository.saveAll(recipes);
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        //detachedRecipe is still POJO, not Hibernate and this is a reason why is called detachedRecipe from Hibernate context
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved recipeId " + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(String id) {
        recipeRepository.deleteById(id);
        log.debug("Recipe has been deleted");
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {

        return recipeRepository.save(recipe);
    }

    private Recipe getRecipe(String id) {
        log.debug("find recipe by id-> " + id);
        return recipeRepository.findById(id).orElseThrow(() -> new NotFoundException("Recipe does not exist for id value-> " + id.toString()));
    }
}
