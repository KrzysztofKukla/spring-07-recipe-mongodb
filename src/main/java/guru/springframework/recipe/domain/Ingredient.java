package guru.springframework.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@Data
//because we have circular reference in Category and Recipe entities we have to avoid in one of them in equalsAndHashCode method
@NoArgsConstructor
@Builder
@AllArgsConstructor
//Ingredient is nested in the list of Mongo Document so it does not get Id value, even if we annotate id with @Id annotation
public class Ingredient {

    private String id = UUID.randomUUID().toString();
    private String description;
    private BigDecimal amount;

//    @DBRef  Reactive driver does NOT support @DBRef
    private UnitOfMeasure unitOfMeasure;
    private Recipe recipe;

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

}
