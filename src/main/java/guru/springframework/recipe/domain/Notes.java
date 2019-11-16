package guru.springframework.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Data
//because we have circular reference in Category and Recipe entities we have to avoid in one of them in equalsAndHashCode method
@EqualsAndHashCode(exclude = {"recipe"})
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
