package guru.springframework.recipe.service;

import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.domain.UnitOfMeasure;

import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
public interface UnitOfMeasureService {
    UnitOfMeasure findByDescription(String description);

    Set<UnitOfMeasureCommand> findAllUom();

}
