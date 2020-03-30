package guru.springframework.recipe.converter;

import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.web.mapper.IngredientToIngredientCommand;
import guru.springframework.recipe.web.mapper.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.recipe.web.model.IngredientDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created by jt on 6/21/17.
 */
public class IngredientToIngredientDtoTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final String UOM_ID = "2";
    public static final String ID_VALUE = "1";

    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullConvert() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void testConvertNullUOM() throws Exception {
        //given
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
//        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUnitOfMeasure(null);
        //when
        final IngredientDto ingredientDto = converter.convert(ingredient);
        //then
        assertNull(ingredientDto.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredientDto.getId());
        // assertEquals(RECIPE, ingredientCommand.get);
        assertEquals(AMOUNT, ingredientDto.getAmount());
        assertEquals(DESCRIPTION, ingredientDto.getDescription());
    }

    @Test
    void testConvertWithUom() throws Exception {
        //given
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
//        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);

        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);

        ingredient.setUnitOfMeasure(uom);
        //when
        final IngredientDto ingredientDto = converter.convert(ingredient);
        //then
        assertEquals(ID_VALUE, ingredientDto.getId());
        assertNotNull(ingredientDto.getUnitOfMeasure());
        assertEquals(UOM_ID, ingredientDto.getUnitOfMeasure().getId());
        // assertEquals(RECIPE, ingredientCommand.get);
        assertEquals(AMOUNT, ingredientDto.getAmount());
        assertEquals(DESCRIPTION, ingredientDto.getDescription());
    }

}