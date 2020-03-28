package guru.springframework.recipe.repository.reactive;

import guru.springframework.recipe.domain.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

/**
 * @author Krzysztof Kukla
 */
//it it Mongo database slice test
//it brings up only limited Spring context
@DataMongoTest
class RecipeReactiveRepositoryIT {

    @Autowired
    private RecipeReactiveRepository recipeReactiveRepository;

    @BeforeEach
    void setUp() {
        //deleteAll just receives Mono
        //to run it we need to use block() method
        recipeReactiveRepository.deleteAll().block();
    }

    @Test
    void recipeSaveTest() throws Exception {
        Recipe recipe = Recipe.builder().description("recipe description").build();

        recipeReactiveRepository.save(recipe).block();
        Assertions.assertEquals(1, recipeReactiveRepository.count().block());
    }

}