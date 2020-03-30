package guru.springframework.recipe;

import guru.springframework.recipe.config.WebConfig;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
public class RouterFunctionTest {

    //this is specify Test Class for Reactive WebFlux stuff
    private WebTestClient webTestClient;

    @Mock
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        WebConfig webConfig = new WebConfig();
        RouterFunction<?> routerFunction = webConfig.routes(recipeService);

        webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
    }

    @Test
    void findAllRecipeTest() {
        Recipe recipe1 = Recipe.builder().build();
        Recipe recipe2 = Recipe.builder().build();

        BDDMockito.when(recipeService.findAll()).thenReturn(Flux.just());

        webTestClient.get().uri("/v1/recipes")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()// it invokes webClient
            .expectStatus().isOk();
    }

    @Test
    void findAllRecipeWithDataTest() {
        Recipe recipe1 = Recipe.builder().build();
        Recipe recipe2 = Recipe.builder().build();

        BDDMockito.when(recipeService.findAll()).thenReturn(Flux.just(recipe1, recipe2));

        webTestClient.get().uri("/v1/recipes")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()// it invokes webClient
            .expectStatus().isOk()
            .expectBodyList(Recipe.class);
    }

}
