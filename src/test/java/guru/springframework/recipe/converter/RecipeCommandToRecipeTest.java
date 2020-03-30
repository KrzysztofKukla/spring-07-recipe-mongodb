package guru.springframework.recipe.converter;

import guru.springframework.recipe.domain.Difficulty;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.web.mapper.CategoryCommandToCategory;
import guru.springframework.recipe.web.mapper.IngredientCommandToIngredient;
import guru.springframework.recipe.web.mapper.NotesCommandToNotes;
import guru.springframework.recipe.web.mapper.RecipeCommandToRecipe;
import guru.springframework.recipe.web.mapper.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.recipe.web.model.CategoryDto;
import guru.springframework.recipe.web.model.IngredientDto;
import guru.springframework.recipe.web.model.NotesDto;
import guru.springframework.recipe.web.model.RecipeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RecipeCommandToRecipeTest {
    public static final String RECIPE_ID = "1";
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final String CAT_ID_1 = "1";
    public static final String CAT_ID2 = "2";
    public static final String INGRED_ID_1 = "3";
    public static final String INGRED_ID_2 = "4";
    public static final String NOTES_ID = "9";

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
            new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
            new NotesCommandToNotes());
    }

    @Test
    void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeDto()));
    }

    @Test
    void convert() throws Exception {
        //given
        final RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(RECIPE_ID);
        recipeDto.setCookTime(COOK_TIME);
        recipeDto.setPrepTime(PREP_TIME);
        recipeDto.setDescription(DESCRIPTION);
        recipeDto.setDifficulty(DIFFICULTY);
        recipeDto.setDirections(DIRECTIONS);
        recipeDto.setServings(SERVINGS);
        recipeDto.setSource(SOURCE);
        recipeDto.setUrl(URL);

        final NotesDto notes = new NotesDto();
        notes.setId(NOTES_ID);

        recipeDto.setNotes(notes);

        final CategoryDto category = new CategoryDto();
        category.setId(CAT_ID_1);

        final CategoryDto category2 = new CategoryDto();
        category2.setId(CAT_ID2);

        recipeDto.getCategories().add(category);
        recipeDto.getCategories().add(category2);

        final IngredientDto ingredient = new IngredientDto();
        ingredient.setId(INGRED_ID_1);

        final IngredientDto ingredient2 = new IngredientDto();
        ingredient2.setId(INGRED_ID_2);

        recipeDto.getIngredients().add(ingredient);
        recipeDto.getIngredients().add(ingredient2);

        //when
        final Recipe recipe = converter.convert(recipeDto);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }

}