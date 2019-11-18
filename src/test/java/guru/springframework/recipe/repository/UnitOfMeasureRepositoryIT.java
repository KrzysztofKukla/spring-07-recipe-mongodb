package guru.springframework.recipe.repository;

import guru.springframework.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

/**
 * @author Krzysztof Kukla
 * <p>
 * This is Integration test
 */

//Spring context will start up
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
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

}