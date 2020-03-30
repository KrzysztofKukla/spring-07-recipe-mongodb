package guru.springframework.recipe.controller;

import guru.springframework.recipe.service.RecipeService;
import guru.springframework.recipe.web.controller.IndexController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author Krzysztof Kukla
 */
@WebFluxTest
@Import(IndexController.class)
class IndexControllerWebFluxTestIT {

    WebTestClient webTestClient;

    @MockBean
    private RecipeService recipeService;

    @Autowired
    private IndexController indexController;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(indexController).build();
    }

//    @Test
//    void testIndexPage() {
//        Recipe first = Recipe.builder().id("1").description("first Descriptuion").build();
//        Recipe second = Recipe.builder().id("2").description("second Descriptuion").build();
//        Recipe third = Recipe.builder().id("3").description("third Descriptuion").build();
//        Set<Recipe> recipes = new HashSet<>(Arrays.asList(first, second, third));
//
//        String categoryDescription = "Italian";
//        Category category = Category.builder().description(categoryDescription).build();
//
//        String unitOfMeasureDescription = "Cup";
//        UnitOfMeasure unitOfMeasure = UnitOfMeasure.builder().description(unitOfMeasureDescription).build();
//
//        BDDMockito.when(recipeService.findAll()).thenReturn(Flux.fromIterable(recipes));
//        BDDMockito.when(categoryService.findByDescription(categoryDescription)).thenReturn(Mono.just(category));
//        BDDMockito.when(unitOfMeasureService.findByDescription(unitOfMeasureDescription)).thenReturn(Mono.just(unitOfMeasure));
//
//        ArgumentCaptor<Flux<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Flux.class);
//
//        Assertions.assertEquals("index", indexController.index(model));
//        BDDMockito.then(recipeService).should().findAll();
//        BDDMockito.then(categoryService).should().findByDescription(ArgumentMatchers.anyString());
//        BDDMockito.then(unitOfMeasureService).should().findByDescription(ArgumentMatchers.anyString());
//        BDDMockito.then(model).should(BDDMockito.times(1))
//            .addAttribute(ArgumentMatchers.matches("recipes"), ArgumentMatchers.anySet());
//        BDDMockito.then(model).should().addAttribute(ArgumentMatchers.matches("recipes"), argumentCaptor.capture());
//        Assertions.assertEquals(recipes.size(), argumentCaptor.getAllValues().size());
//    }

    @Test
    void testMockMvc() throws Exception {
        //old mockMvc is tied to Servlet API - blocking, not used in Reactive WebFlux
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
//        mockMvc.perform(MockMvcRequestBuilders.get("/"))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andExpect((MockMvcResultMatchers.view().name("index")));

        webTestClient.get().uri("/")
            .exchange()
            .expectStatus().isOk()
            .expectBody();
    }

}