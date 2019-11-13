package guru.springframework.recipe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * @author Krzysztof Kukla
 */
@Entity
@Getter
@Setter
public class Notes {


    @Id
    //IDENTITY is a special type that will support the automatic generation of a sequence
    // for example MySql
    // added in Oracle 12
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    //Lob allows to store more than 255 characters
    @Lob
    private String recipeNotes;
}
