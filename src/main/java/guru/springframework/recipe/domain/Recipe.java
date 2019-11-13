package guru.springframework.recipe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    //LAZY is by default to OneToMany
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe" , fetch = FetchType.LAZY)
    //Set in preferred to have unique objects
    private Set<Ingredient> ingredients;

    //large object
    //binary large object filed (BLOB)
    @Lob
    private Byte[] image;

    // ORDINAL is default ( is number ) and will be change when we will put something between them
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
}
