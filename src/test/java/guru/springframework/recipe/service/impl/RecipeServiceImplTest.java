package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repository.RecipeRepository;
import guru.springframework.recipe.service.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

/**
 * @author Krzysztof Kukla
 */
//@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
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
    }

}