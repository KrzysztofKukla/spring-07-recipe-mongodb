package guru.springframework.recipe.controller;

import guru.springframework.recipe.service.IngredientService;
import guru.springframework.recipe.service.RecipeService;
import guru.springframework.recipe.service.UnitOfMeasureService;
import guru.springframework.recipe.web.controller.IngredientController;
import guru.springframework.recipe.web.model.IngredientDto;
import guru.springframework.recipe.web.model.RecipeDto;
import guru.springframework.recipe.web.model.UnitOfMeasureDto;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
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
        RecipeDto recipeDto = RecipeDto.builder().id("1").description("description").build();

        BDDMockito.when(recipeService.findRecipeCommandById("1")).thenReturn(Mono.just(recipeDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.model().attribute("recipe", Matchers.any(RecipeDto.class)))
            .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/list"));
        BDDMockito.then(recipeService).should().findRecipeCommandById(anyString());
    }

    @Test
    void showIngredientTest() throws Exception {
        //given
        IngredientDto ingredientDto = IngredientDto.builder().id("1").description("description").build();

        //when
        BDDMockito.when(ingredientService.findByRecipeIdAndIngredientId("1", "1")).thenReturn(Mono.just(ingredientDto));

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/1/show"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("ingredient", Matchers.any(IngredientDto.class)))
            .andExpect(view().name("recipe/ingredient/show"));
        BDDMockito.then(ingredientService.findByRecipeIdAndIngredientId(anyString(), anyString()));

    }

    @Test
    void updateRecipeIngredientTest() throws Exception {
        //given
        IngredientDto ingredientDto = IngredientDto.builder().id("1").description("description").build();
        UnitOfMeasureDto unitOfMeasureDto1 = UnitOfMeasureDto.builder().id("1").description("desciption1").build();
        UnitOfMeasureDto unitOfMeasureDto2 = UnitOfMeasureDto.builder().id("2").description("desciption2").build();

        //when
        BDDMockito.when(ingredientService.findByRecipeIdAndIngredientId(anyString(), anyString())).thenReturn(Mono.just(ingredientDto));
        BDDMockito.when(unitOfMeasureService.findAllUom()).thenReturn(Flux.just(unitOfMeasureDto1, unitOfMeasureDto2));

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/1/update"))
            .andExpect(model().attribute("ingredient", Matchers.any(IngredientDto.class)))
            .andExpect(model().attribute("uomList", Matchers.any(Set.class)))
            .andExpect(view().name("recipe/ingredient/ingredientform"));
        BDDMockito.then(ingredientService).should().findByRecipeIdAndIngredientId(anyString(), anyString());
        BDDMockito.then(unitOfMeasureService).should().findAllUom();
    }

    @Test
    void saveOrUpdateTest() throws Exception {
        String commandId = "1";
        String recipeId = "1";
        IngredientDto ingredientDto = IngredientDto.builder().id(commandId).recipeId(recipeId).description("desciption").build();

        BDDMockito.when(ingredientService.saveIngredientCommand(ArgumentMatchers.any(IngredientDto.class))).thenReturn(Mono.just(ingredientDto));

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/1/ingredient"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/recipe/" + ingredientDto.getRecipeId() + "/ingredient/" + ingredientDto.getId() + "/show"));
        BDDMockito.then(ingredientService).should().saveIngredientCommand(ArgumentMatchers.any(IngredientDto.class));
    }

    @Test
    void newIngredientTest() throws Exception {
        RecipeDto recipeDto = RecipeDto.builder().id("1").description("description").build();
        UnitOfMeasureDto unitOfMeasureDto1 = UnitOfMeasureDto.builder().id("1").build();
        UnitOfMeasureDto unitOfMeasureDto2 = UnitOfMeasureDto.builder().id("2").build();

        BDDMockito.when(unitOfMeasureService.findAllUom()).thenReturn(Flux.just(unitOfMeasureDto1, unitOfMeasureDto2));

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/new"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("ingredient", Matchers.any(IngredientDto.class)))
            .andExpect(model().attribute("uomList", Matchers.any(Set.class)))
            .andExpect(view().name("recipe/ingredient/ingredientform"));

        BDDMockito.then(unitOfMeasureService).should().findAllUom();
    }

    @Test
    void deleteIngredientTest() throws Exception {
        Long recipeId = 1L;
        Long ingredientId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/1/delete"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/recipe/" + recipeId + "/ingredients"));
        BDDMockito.then(ingredientService).should().deleteIngredientById(anyString(), anyString());
    }

}
