package guru.springframework.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

/**
 * @author Krzysztof Kukla
 */
@Data
//because we have circular reference in Category and Recipe entities we have to avoid in one of them in equalsAndHashCode method
@EqualsAndHashCode(exclude = {"recipe"})
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Ingredient {

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }
    private String id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasure unitOfMeasure;
    private Recipe recipe;
}
