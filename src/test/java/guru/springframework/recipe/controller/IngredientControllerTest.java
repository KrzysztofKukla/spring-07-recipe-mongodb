package guru.springframework.recipe.controller;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.service.IngredientService;
import guru.springframework.recipe.service.RecipeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private IngredientController ingredientController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    void ingredientList() throws Exception {
        RecipeCommand recipeCommand = RecipeCommand.builder().id(1L).description("description").build();

        BDDMockito.when(recipeService.findRecipeCommandById(1L)).thenReturn(recipeCommand);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.model().attribute("recipe", Matchers.any(RecipeCommand.class)))
            .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/list"));
        BDDMockito.then(recipeService).should().findRecipeCommandById(ArgumentMatchers.anyLong());
    }

    @Test
    void showIngredientTest() throws Exception {
        //given
        IngredientCommand ingredientCommand = IngredientCommand.builder().id(1L).description("description").build();

        //when
        BDDMockito.when(ingredientService.findByRecipeIdAndIngredientId(1L, 1L)).thenReturn(ingredientCommand);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/1/show"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("ingredient", Matchers.any(IngredientCommand.class)))
            .andExpect(view().name("recipe/ingredient/show"));
        BDDMockito.then(ingredientService.findByRecipeIdAndIngredientId(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()));

    }

}