package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.converter.IngredientToIngredientCommand;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.repository.IngredientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private IngredientToIngredientCommand ingredientToIngredientCommand;

    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @Test
    void findByRecipeIdAndIngredientId() {
        long ingredientId = 1L;
        long recipeId = 1L;
        Ingredient ingredient = Ingredient.builder().id(ingredientId).description("description").build();
        IngredientCommand ingredientCommand = IngredientCommand.builder().id(ingredientId).recipeId(recipeId).description("description").build();

        BDDMockito.when(ingredientRepository.findByRecipeIdAndId(recipeId, ingredientId)).thenReturn(ingredient);
        BDDMockito.when(ingredientToIngredientCommand.convert(ArgumentMatchers.any(Ingredient.class))).thenReturn(ingredientCommand);
        IngredientCommand foundIngredientCommand = ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId);

        Assertions.assertEquals(ingredientCommand, foundIngredientCommand);
        BDDMockito.then(ingredientRepository).should().findByRecipeIdAndId(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong());
        BDDMockito.then(ingredientToIngredientCommand.convert(ArgumentMatchers.any(Ingredient.class)));
    }

}