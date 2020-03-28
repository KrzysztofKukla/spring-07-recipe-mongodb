package guru.springframework.recipe.repository;

import guru.springframework.recipe.bootstrap.RecipeBootstrap;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repository.reactive.UnitOfMeasureReactiveRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

/**
 * @author Krzysztof Kukla
 * <p>
 * This is Integration test
 */

//Spring context will start up
@ExtendWith(SpringExtension.class)
@DataMongoTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;
    @Autowired
    private UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    /**
     * MongoDb does NOT transactions
     * in JPA we have transactions, so default behaviour of Spring is to roll back after each test method
     */
    @BeforeEach
    void setUp() {
        resetAll();
        RecipeBootstrap recipeBootstrap = new RecipeBootstrap(categoryRepository, recipeRepository, unitOfMeasureRepository, unitOfMeasureReactiveRepository);
        recipeBootstrap.onApplicationEvent(null);
    }

    @Test
    void findByDescription() {
        Optional<UnitOfMeasure> result = unitOfMeasureRepository.findByDescription("Pinch");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Pinch", result.get().getDescription());
    }

    @Test
//    @DirtiesContext is used when the context is modified by previous tests and need to be clear
        // it takes more time than usually
    void findByDescriptionCup() {
        Optional<UnitOfMeasure> result = unitOfMeasureRepository.findByDescription("Cup");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Cup", result.get().getDescription());
    }

    private void resetAll() {
        recipeRepository.deleteAll();
        unitOfMeasureRepository.deleteAll();
        categoryRepository.deleteAll();
    }

}
