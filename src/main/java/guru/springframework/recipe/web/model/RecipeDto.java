package guru.springframework.recipe.web.model;

import guru.springframework.recipe.domain.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Krzysztof Kukla
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDto {
    private String id;

    @NotBlank
    @Size(min = 3, max = 255) // Varchar in database allows to have max 255 characters
    private String description;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @Min(1)
    @Max(100)
    private Integer servings;

    private String source;

    @URL // it allows blank value
    private String url;
    @NotBlank
    private String directions;

    //Thymeleaf does not bind to Set, but only will bind to the list
    private List<IngredientDto> ingredients = new ArrayList<>();
    private Difficulty difficulty;
    private Byte[] image;
    private NotesDto notes;
    private List<CategoryDto> categories = new ArrayList<>();

}
