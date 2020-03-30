package guru.springframework.recipe.config;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.service.RecipeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author Krzysztof Kukla
 */
@Configuration
public class WebConfig {

    @Bean
    //this is specific for Reactive WebFlux context as a route
    //here we define ServerResponse with ok status, contentType and body for this particular url request GET '/api/recipes'
    //for body we are going to call out 'recipeService.findAll()
    public RouterFunction<?> routes(RecipeService recipeService) {
        return RouterFunctions.route(RequestPredicates.GET("/v1/recipes"),
            serverRequest -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(recipeService.findAll(), Recipe.class));
    }

}
