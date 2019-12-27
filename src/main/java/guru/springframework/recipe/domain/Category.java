package guru.springframework.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krzysztof Kukla
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

//because we have circular reference in Category and Recipe entities we have to avoid in one of them in equalsAndHashCode method
@EqualsAndHashCode(exclude = {"recipes"})
@Document
public class Category {
    @Id
    private String id;
    private String description;
    @DBRef
    private List<Recipe> recipes = new ArrayList<>();
}
