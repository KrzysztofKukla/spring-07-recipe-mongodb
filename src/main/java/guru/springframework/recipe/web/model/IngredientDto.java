package guru.springframework.recipe.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

/**
 * @author Krzysztof Kukla
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientDto {

    @Null
    private String id;
    private String recipeId;
    @NotBlank
    private String description;
    @NotNull
    @Min(1)
    private BigDecimal amount;
    @NotNull
    private UnitOfMeasureDto unitOfMeasure;

}
