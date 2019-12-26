package guru.springframework.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * @author Krzysztof Kukla
 */
@Data
//because we have circular reference in Category and Recipe entities we have to avoid in one of them in equalsAndHashCode method
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {
    private String id;
    private Recipe recipe;
    private String recipeNotes;
}
