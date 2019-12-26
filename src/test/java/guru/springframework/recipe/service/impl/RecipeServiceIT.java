package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.converter.RecipeCommandToRecipe;
import guru.springframework.recipe.converter.RecipeToRecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repository.RecipeRepository;
import guru.springframework.recipe.service.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith({SpringExtension.class})
@SpringBootTest
class RecipeServiceIT {

    private static final String DESCRIPTION = "New Description";

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    void testSaveOfDescription() throws Exception {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }

    @Test
    void findCommandByIdTest() {
        Recipe recipe = Recipe.builder().id("1").description("description").build();

        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);

        Assertions.assertEquals(recipe.getId(), recipeCommand.getId());
        Assertions.assertEquals(recipe.getDescription(), recipeCommand.getDescription());

    }

}