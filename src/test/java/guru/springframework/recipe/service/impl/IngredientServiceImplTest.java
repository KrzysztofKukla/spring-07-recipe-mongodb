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
        long ingredientId = 1L;
        long recipeId = 1L;
        Ingredient ingredient = Ingredient.builder().id(ingredientId).description("description").build();
        IngredientCommand ingredientCommand = IngredientCommand.builder().id(ingredientId).recipeId(recipeId).description("description").build();

        BDDMockito.when(ingredientRepository.findByRecipeIdAndId(recipeId, ingredientId)).thenReturn(Optional.of(ingredient));
        BDDMockito.when(ingredientToIngredientCommand.convert(ArgumentMatchers.any(Ingredient.class))).thenReturn(ingredientCommand);
        IngredientCommand foundIngredientCommand = ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId);

        Assertions.assertEquals(ingredientCommand, foundIngredientCommand);
        BDDMockito.then(ingredientRepository).should().findByRecipeIdAndId(anyLong(), anyLong());
        BDDMockito.then(ingredientToIngredientCommand.convert(ArgumentMatchers.any(Ingredient.class)));
    }

    @Test
    void saveIngredientCommandIngredientPresentTest() {
        long recipeId = 1L;
        long ingredientId = 1L;
        Recipe recipe = Recipe.builder().id(recipeId).build();
        Ingredient ingredient = Ingredient.builder().id(ingredientId).recipe(recipe).description("description").build();
        UnitOfMeasure unitOfMeasure = UnitOfMeasure.builder().id(1L).build();
        UnitOfMeasureCommand unitOfMeasureCommand = UnitOfMeasureCommand.builder().id(1L).build();
        IngredientCommand ingredientCommand = IngredientCommand.builder().id(1L).recipeId(1L).unitOfMeasure(unitOfMeasureCommand).build();

        BDDMockito.when(ingredientRepository.findByRecipeIdAndId(anyLong(), anyLong())).thenReturn(Optional.of(ingredient));
        BDDMockito.when(unitOfMeasureRepository.findById(anyLong())).thenReturn(Optional.of(unitOfMeasure));
        ingredientService.saveIngredientCommand(ingredientCommand);

        BDDMockito.then(ingredientRepository).should().findByRecipeIdAndId(anyLong(), anyLong());
        BDDMockito.then(ingredientCommandToIngredient).should(BDDMockito.times(0))
            .convert(ArgumentMatchers.any(IngredientCommand.class));
        BDDMockito.then(ingredientRepository).should().save(ArgumentMatchers.any(Ingredient.class));
    }

    @Test
    void saveIngredientCommandIngredientNotPresentTest() {
        long recipeId = 1L;
        long ingredientId = 1L;
        Recipe recipe = Recipe.builder().id(recipeId).build();
        Ingredient ingredient = Ingredient.builder().id(ingredientId).recipe(recipe).description("description").build();
        UnitOfMeasureCommand unitOfMeasureCommand = UnitOfMeasureCommand.builder().id(1L).build();
        IngredientCommand ingredientCommand = IngredientCommand.builder().id(1L).recipeId(1L).unitOfMeasure(unitOfMeasureCommand).build();

        BDDMockito.when(ingredientRepository.findByRecipeIdAndId(anyLong(), anyLong())).thenReturn(Optional.empty());
        BDDMockito.when(ingredientCommandToIngredient.convert(ArgumentMatchers.any(IngredientCommand.class))).thenReturn(ingredient);
        ingredientService.saveIngredientCommand(ingredientCommand);

        BDDMockito.then(ingredientRepository).should().findByRecipeIdAndId(anyLong(), anyLong());
        BDDMockito.then(unitOfMeasureRepository).should(BDDMockito.times(0)).findById(anyLong());
        BDDMockito.then(ingredientCommandToIngredient).should(BDDMockito.times(1))
            .convert(ArgumentMatchers.any(IngredientCommand.class));
        BDDMockito.then(ingredientRepository).should().save(ArgumentMatchers.any(Ingredient.class));
    }

    @Test
    void deleteIngredientTest() {
        Long recipeId = 1L;
        Long ingredientId = 1L;
        Ingredient ingredient = Ingredient.builder().id(1L).build();

        BDDMockito.when(ingredientRepository.findByRecipeIdAndId(anyLong(), anyLong())).thenReturn(Optional.of(ingredient));
        ingredientService.deleteIngredientById(recipeId, ingredientId);

        BDDMockito.then(ingredientRepository).should().findByRecipeIdAndId(anyLong(), anyLong());
        BDDMockito.then(ingredientRepository).should().delete(any(Ingredient.class));
    }

}