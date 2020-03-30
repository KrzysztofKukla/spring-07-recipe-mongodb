package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repository.RecipeRepository;
import guru.springframework.recipe.service.RecipeService;
import guru.springframework.recipe.web.mapper.RecipeCommandToRecipe;
import guru.springframework.recipe.web.mapper.RecipeToRecipeCommand;
import guru.springframework.recipe.web.model.RecipeDto;
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
        RecipeDto testRecipeDto = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeDto.setDescription(DESCRIPTION);
        RecipeDto savedRecipeDto = recipeService.saveRecipeCommand(testRecipeDto).block();

        //then
        assertEquals(DESCRIPTION, savedRecipeDto.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeDto.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeDto.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeDto.getIngredients().size());
    }

    @Test
    void findCommandByIdTest() {
        Recipe recipe = Recipe.builder().id("1").description("description").build();

        RecipeDto recipeDto = recipeToRecipeCommand.convert(recipe);

        Assertions.assertEquals(recipe.getId(), recipeDto.getId());
        Assertions.assertEquals(recipe.getDescription(), recipeDto.getDescription());

    }

}