package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Test
    void findById() {
        long id = 1L;
        Recipe recipe = Recipe.builder().id(id).build();

        BDDMockito.when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));

        Assertions.assertNotNull(recipeService.findById(id));
        BDDMockito.then(recipeRepository).should(BDDMockito.times(1)).findById(ArgumentMatchers.anyLong());
    }

    @Test
    void findByIdDoesNotExist() {
        long id = 1L;
        BDDMockito.when(recipeRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> recipeService.findById(id));
        BDDMockito.then(recipeRepository).should().findById(id);
    }

    @Test
    void getRecipeCommandByIdTest() {

    }


    @Test
    void shouldFindAllRecipes() {
        Recipe first = Recipe.builder().id(1L).description("first Descriptuion").build();
        Recipe second = Recipe.builder().id(2L).description("second Descriptuion").build();
        Recipe third = Recipe.builder().id(3L).description("third Descriptuion").build();
        List<Recipe> recipeList = Arrays.asList(first, second, third);

        BDDMockito.when(recipeRepository.findAll()).thenReturn(recipeList);

        Assertions.assertEquals(recipeList.size(), recipeService.findAll().size());
        BDDMockito.then(recipeRepository).should().findAll();
    }

    @Test
    void saveAll() {
        //given
        Recipe recipe1 = Recipe.builder().id(1L).description("first").build();
        Recipe recipe2 = Recipe.builder().id(1L).description("second").build();
        List<Recipe> recipeList = Arrays.asList(recipe1, recipe2);

        //when
        BDDMockito.when(recipeRepository.findAll()).thenReturn(recipeList);

        //then
        org.assertj.core.api.Assertions.assertThat(recipeService.findAll()).hasSize(recipeList.size());
        BDDMockito.then(recipeRepository).should().findAll();
    }

    @Test
    void deleteById() {
        long id = 1L;
        recipeService.deleteById(id);

        BDDMockito.then(recipeRepository).should().deleteById(id);
    }

}