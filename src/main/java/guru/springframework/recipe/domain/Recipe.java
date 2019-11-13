package guru.springframework.recipe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    //TODO
//    private Difficulty difficulty;

    //LAZY is by default to OneToMany
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe" , fetch = FetchType.LAZY)
    //Set in preferred to have unique objects
    private Set<Ingredient> ingredients;

    //large object
    //binary large object filed (BLOB)
    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
}
