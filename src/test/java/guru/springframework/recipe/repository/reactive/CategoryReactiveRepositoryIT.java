package guru.springframework.recipe.repository.reactive;

import guru.springframework.recipe.domain.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Krzysztof Kukla
 */
//it it Mongo database slice test
//it brings up only limited Spring context
class CategoryReactiveRepositoryIT {

    @Autowired
    private CategoryReactiveRepository categoryReactiveRepository;

    @BeforeEach
    void setUp() {
        categoryReactiveRepository.deleteAll()
            .block();
    }

    @Test
    void findByDescription() {
        String categoryDescription = "some category";
        Category category = Category.builder().description(categoryDescription).build();
        categoryReactiveRepository.save(category).block();

        Category foundCategory = categoryReactiveRepository.findByDescription(categoryDescription).block();
        Assertions.assertNotNull(foundCategory);
        Assertions.assertNotNull(foundCategory.getId());
        Assertions.assertEquals("some category", foundCategory.getDescription());
    }

}