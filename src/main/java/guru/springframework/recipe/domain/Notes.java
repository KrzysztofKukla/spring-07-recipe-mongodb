package guru.springframework.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

/**
 * @author Krzysztof Kukla
 */
@Data
//because we have circular reference in Category and Recipe entities we have to avoid in one of them in equalsAndHashCode method
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {
    @Id
    private String id;
    private Recipe recipe;
    private String recipeNotes;
}
