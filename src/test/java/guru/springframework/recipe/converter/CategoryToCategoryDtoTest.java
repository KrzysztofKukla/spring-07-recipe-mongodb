package guru.springframework.recipe.converter;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.web.mapper.CategoryToCategoryCommand;
import guru.springframework.recipe.web.model.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created by jt on 6/21/17.
 */
public class CategoryToCategoryDtoTest {

    public static final String ID_VALUE = "1";
    public static final String DESCRIPTION = "descript";
    CategoryToCategoryCommand convter;

    @BeforeEach
    void setUp() throws Exception {
        convter = new CategoryToCategoryCommand();
    }

    @Test
    void testNullObject() throws Exception {
        assertNull(convter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(convter.convert(new Category()));
    }

    @Test
    void convert() throws Exception {
        //given
        final Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        final CategoryDto categoryDto = convter.convert(category);

        //then
        assertEquals(ID_VALUE, categoryDto.getId());
        assertEquals(DESCRIPTION, categoryDto.getDescription());

    }

}