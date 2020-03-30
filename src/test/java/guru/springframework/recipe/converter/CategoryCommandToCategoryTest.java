package guru.springframework.recipe.converter;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.web.mapper.CategoryCommandToCategory;
import guru.springframework.recipe.web.model.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CategoryCommandToCategoryTest {

    public static final String ID_VALUE = "1";
    public static final String DESCRIPTION = "description";
    CategoryCommandToCategory conveter;

    @BeforeEach
    void setUp() throws Exception {
        conveter = new CategoryCommandToCategory();
    }

    @Test
    void testNullObject() throws Exception {
        assertNull(conveter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(conveter.convert(new CategoryDto()));
    }

    @Test
    void convert() throws Exception {
        //given
        final CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(ID_VALUE);
        categoryDto.setDescription(DESCRIPTION);

        //when
        final Category category = conveter.convert(categoryDto);

        //then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }

}