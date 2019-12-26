package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.converter.IngredientCommandToIngredient;
import guru.springframework.recipe.converter.IngredientToIngredientCommand;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repository.IngredientRepository;
import guru.springframework.recipe.repository.UnitOfMeasureRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    private IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    private IngredientCommandToIngredient ingredientCommandToIngredient;

    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @Test
    void findByRecipeIdAndIngredientId() {
        String ingredientId = "1";
        String recipeId = "1";
        Ingredient ingredient = Ingredient.builder().id(ingredientId).description("description").build();
        IngredientCommand ingredientCommand = IngredientCommand.builder().id(ingredientId).recipeId(recipeId).description("description").build();

        BDDMockito.when(ingredientRepository.findByRecipeIdAndId(recipeId, ingredientId)).thenReturn(Optional.of(ingredient));
        BDDMockito.when(ingredientToIngredientCommand.convert(ArgumentMatchers.any(Ingredient.class))).thenReturn(ingredientCommand);
        IngredientCommand foundIngredientCommand = ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId);

        Assertions.assertEquals(ingredientCommand, foundIngredientCommand);
        BDDMockito.then(ingredientRepository).should().findByRecipeIdAndId(anyString(), anyString());
        BDDMockito.then(ingredientToIngredientCommand.convert(ArgumentMatchers.any(Ingredient.class)));
    }

    @Test
    void saveIngredientCommandIngredientPresentTest() {
        String recipeId = "1";
        String ingredientId = "1";
        Recipe recipe = Recipe.builder().id(recipeId).build();
        Ingredient ingredient = Ingredient.builder().id(ingredientId).recipe(recipe).description("description").build();
        UnitOfMeasure unitOfMeasure = UnitOfMeasure.builder().id("1").build();
        UnitOfMeasureCommand unitOfMeasureCommand = UnitOfMeasureCommand.builder().id("1").build();
        IngredientCommand ingredientCommand = IngredientCommand.builder().id("1").recipeId("1").unitOfMeasure(unitOfMeasureCommand).build();

        BDDMockito.when(ingredientRepository.findByRecipeIdAndId(anyString(), anyString())).thenReturn(Optional.of(ingredient));
        BDDMockito.when(unitOfMeasureRepository.findById(anyString())).thenReturn(Optional.of(unitOfMeasure));
        ingredientService.saveIngredientCommand(ingredientCommand);

        BDDMockito.then(ingredientRepository).should().findByRecipeIdAndId(anyString(), anyString());
        BDDMockito.then(ingredientCommandToIngredient).should(BDDMockito.times(0))
            .convert(ArgumentMatchers.any(IngredientCommand.class));
        BDDMockito.then(ingredientRepository).should().save(ArgumentMatchers.any(Ingredient.class));
    }

    @Test
    void saveIngredientCommandIngredientNotPresentTest() {
        String recipeId = "1";
        String ingredientId = "1";
        Recipe recipe = Recipe.builder().id(recipeId).build();
        Ingredient ingredient = Ingredient.builder().id(ingredientId).recipe(recipe).description("description").build();
        UnitOfMeasureCommand unitOfMeasureCommand = UnitOfMeasureCommand.builder().id("1").build();
        IngredientCommand ingredientCommand = IngredientCommand.builder().id("1").recipeId("1").unitOfMeasure(unitOfMeasureCommand).build();

        BDDMockito.when(ingredientRepository.findByRecipeIdAndId(anyString(), anyString())).thenReturn(Optional.empty());
        BDDMockito.when(ingredientCommandToIngredient.convert(ArgumentMatchers.any(IngredientCommand.class))).thenReturn(ingredient);
        ingredientService.saveIngredientCommand(ingredientCommand);

        BDDMockito.then(ingredientRepository).should().findByRecipeIdAndId(anyString(), anyString());
        BDDMockito.then(unitOfMeasureRepository).should(BDDMockito.times(0)).findById(anyString());
        BDDMockito.then(ingredientCommandToIngredient).should(BDDMockito.times(1))
            .convert(ArgumentMatchers.any(IngredientCommand.class));
        BDDMockito.then(ingredientRepository).should().save(ArgumentMatchers.any(Ingredient.class));
    }

    @Test
    void deleteIngredientTest() {
        String recipeId = "1";
        String ingredientId = "1";
        Ingredient ingredient = Ingredient.builder().id("1").build();

        BDDMockito.when(ingredientRepository.findByRecipeIdAndId(anyString(), anyString())).thenReturn(Optional.of(ingredient));
        ingredientService.deleteIngredientById(recipeId, ingredientId);

        BDDMockito.then(ingredientRepository).should().findByRecipeIdAndId(anyString(), anyString());
        BDDMockito.then(ingredientRepository).should().delete(any(Ingredient.class));
    }

}