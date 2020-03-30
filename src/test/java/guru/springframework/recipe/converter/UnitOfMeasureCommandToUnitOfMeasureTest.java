package guru.springframework.recipe.converter;

import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.web.mapper.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.recipe.web.model.UnitOfMeasureDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final String DESCRIPTION = "description";
    public static final String LONG_VALUE = "1";

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();

    }

    @Test
    void testNullParamter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureDto()));
    }

    @Test
    void convert() throws Exception {
        //given
        final UnitOfMeasureDto command = new UnitOfMeasureDto();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        final UnitOfMeasure uom = converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());

    }

}