package guru.springframework.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krzysztof Kukla
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Document
public class Recipe {
    @Id
    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private List<Ingredient> ingredients = new ArrayList<>();
    private Byte[] image;
    private Difficulty difficulty;
    private Notes notes;

//    @DBRef  Reactive driver does NOT support @DBRef
    private List<Category> categories = new ArrayList<>();

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
//            notes.setRecipe(this);
        }
    }

    public Recipe addIngredient(Ingredient ingredient){
//        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
