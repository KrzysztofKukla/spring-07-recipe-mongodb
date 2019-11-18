package guru.springframework.recipe.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Krzysztof Kukla
 */
class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    void getId() {
        Long id = 4L;
        category.setId(id);
        Assertions.assertEquals(id, category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }

}