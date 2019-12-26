package guru.springframework.recipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Krzysztof Kukla
 */
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
    private String id;
    private RecipeCommand recipe;
    private String recipeNotes;

}
