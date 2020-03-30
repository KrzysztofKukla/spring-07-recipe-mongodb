package guru.springframework.recipe.web.mapper;

import guru.springframework.recipe.domain.Notes;
import guru.springframework.recipe.web.model.NotesDto;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class NotesCommandToNotes implements Converter<NotesDto, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesDto source) {
        if (source == null) {
            return null;
        }

        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }

}
