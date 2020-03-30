package guru.springframework.recipe.converter;

import guru.springframework.recipe.domain.Notes;
import guru.springframework.recipe.web.mapper.NotesToNotesCommand;
import guru.springframework.recipe.web.model.NotesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created by jt on 6/21/17.
 */
public class NotesToNotesDtoTest {

    public static final String ID_VALUE = "1";
    public static final String RECIPE_NOTES = "Notes";
    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    void convert() throws Exception {
        //given
        final Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        final NotesDto notesDto = converter.convert(notes);

        //then
        assertEquals(ID_VALUE, notesDto.getId());
        assertEquals(RECIPE_NOTES, notesDto.getRecipeNotes());
    }

    @Test
    void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }

}