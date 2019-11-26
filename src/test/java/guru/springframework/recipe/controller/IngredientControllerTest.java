package guru.springframework.recipe.controller;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.service.IngredientService;
import guru.springframework.recipe.service.RecipeService;
import guru.springframework.recipe.service.UnitOfMeasureService;
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
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

    @Mock
    private UnitOfMeasureService unitOfMeasureService;

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
        BDDMockito.then(recipeService).should().findRecipeCommandById(anyLong());
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
        BDDMockito.then(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong()));

    }

    @Test
    void updateRecipeIngredientTest() throws Exception {
        //given
        IngredientCommand ingredientCommand = IngredientCommand.builder().id(1L).description("description").build();
        UnitOfMeasureCommand unitOfMeasureCommand1 = UnitOfMeasureCommand.builder().id(1L).description("desciption1").build();
        UnitOfMeasureCommand unitOfMeasureCommand2 = UnitOfMeasureCommand.builder().id(2L).description("desciption2").build();
        Set<UnitOfMeasureCommand> unitOfMeasuresCommandSet = new HashSet<>(Arrays.asList(unitOfMeasureCommand1, unitOfMeasureCommand2));

        //when
        BDDMockito.when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        BDDMockito.when(unitOfMeasureService.findAllUom()).thenReturn(unitOfMeasuresCommandSet);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/1/update"))
            .andExpect(model().attribute("ingredient", Matchers.any(IngredientCommand.class)))
            .andExpect(model().attribute("uomList", Matchers.any(Set.class)))
            .andExpect(view().name("recipe/ingredient/ingredientform"));
        BDDMockito.then(ingredientService).should().findByRecipeIdAndIngredientId(anyLong(), anyLong());
        BDDMockito.then(unitOfMeasureService).should().findAllUom();
    }

    @Test
    void saveOrUpdateTest() throws Exception {
        long commandId = 1L;
        long recipeId = 1L;
        IngredientCommand ingredientCommand = IngredientCommand.builder().id(commandId).recipeId(recipeId).description("desciption").build();

        BDDMockito.when(ingredientService.saveIngredientCommand(ArgumentMatchers.any(IngredientCommand.class))).thenReturn(ingredientCommand);

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/1/ingredient"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/recipe/" + ingredientCommand.getRecipeId() + "/ingredient/" + ingredientCommand.getId() + "/show"));
        BDDMockito.then(ingredientService).should().saveIngredientCommand(ArgumentMatchers.any(IngredientCommand.class));
    }
}