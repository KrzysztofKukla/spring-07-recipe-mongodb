package guru.springframework.recipe.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Krzysztof Kukla
 */
@Getter
@Setter
@NoArgsConstructor
public class NotesDto {
    private String id;
    private RecipeDto recipe;
    private String recipeNotes;

}
