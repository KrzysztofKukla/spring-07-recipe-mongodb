package guru.springframework.recipe.repository.reactive;

import guru.springframework.recipe.domain.UnitOfMeasure;
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
class UnitOfMeasureReactiveRepositoryTest {

    @Autowired
    private UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @BeforeEach
    void setUp() {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    void findByDescription() {
        String descritpion = "some";
        UnitOfMeasure unitOfMeasure = UnitOfMeasure.builder().description(descritpion).build();

        UnitOfMeasure savedUnitOfMeasure = unitOfMeasureReactiveRepository.save(unitOfMeasure)
            .block();

        UnitOfMeasure foundUnitOfMeasure = unitOfMeasureReactiveRepository.findByDescription(descritpion)
            .block();
        Assertions.assertNotNull(foundUnitOfMeasure);
        Assertions.assertNotNull(foundUnitOfMeasure.getId());
        Assertions.assertEquals("some", foundUnitOfMeasure.getDescription());
    }

}