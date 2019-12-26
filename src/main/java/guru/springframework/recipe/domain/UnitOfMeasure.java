package guru.springframework.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Krzysztof Kukla
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitOfMeasure {
    private String id;
    private String description;
}
