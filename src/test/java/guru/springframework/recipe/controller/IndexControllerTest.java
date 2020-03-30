package guru.springframework.recipe.controller;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.service.CategoryService;
import guru.springframework.recipe.service.RecipeService;
import guru.springframework.recipe.service.UnitOfMeasureService;
import guru.springframework.recipe.web.controller.IndexController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
class IndexControllerTest {

    private IndexController indexController;

    @Mock
    private CategoryService categoryService;

    @Mock
    private UnitOfMeasureService unitOfMeasureService;

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(categoryService, unitOfMeasureService, recipeService);
    }

    @Test
    void testIndexPage() {
        Recipe first = Recipe.builder().id("1").description("first Descriptuion").build();
        Recipe second = Recipe.builder().id("2").description("second Descriptuion").build();
        Recipe third = Recipe.builder().id("3").description("third Descriptuion").build();
        Set<Recipe> recipes = new HashSet<>(Arrays.asList(first, second, third));

        String categoryDescription = "Italian";
        Category category = Category.builder().description(categoryDescription).build();

        String unitOfMeasureDescription = "Cup";
        UnitOfMeasure unitOfMeasure = UnitOfMeasure.builder().description(unitOfMeasureDescription).build();

        BDDMockito.when(recipeService.findAll()).thenReturn(Flux.fromIterable(recipes));
        BDDMockito.when(categoryService.findByDescription(categoryDescription)).thenReturn(Mono.just(category));
        BDDMockito.when(unitOfMeasureService.findByDescription(unitOfMeasureDescription)).thenReturn(Mono.just(unitOfMeasure));

        ArgumentCaptor<Flux<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Flux.class);

        Assertions.assertEquals("index", indexController.index(model));
        BDDMockito.then(recipeService).should().findAll();
        BDDMockito.then(categoryService).should().findByDescription(ArgumentMatchers.anyString());
        BDDMockito.then(unitOfMeasureService).should().findByDescription(ArgumentMatchers.anyString());
        BDDMockito.then(model).should(BDDMockito.times(1))
            .addAttribute(ArgumentMatchers.matches("recipes"), ArgumentMatchers.anySet());
        BDDMockito.then(model).should().addAttribute(ArgumentMatchers.matches("recipes"), argumentCaptor.capture());
        Assertions.assertEquals(recipes.size(), argumentCaptor.getAllValues().size());
    }

    @Test
    void testMockMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect((MockMvcResultMatchers.view().name("index")));

    }

}