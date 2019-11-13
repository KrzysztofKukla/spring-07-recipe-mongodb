package guru.springframework.recipe.service;

import guru.springframework.recipe.domain.UnitOfMeasure;

/**
 * @author Krzysztof Kukla
 */
public interface UnitOfMeasureService {
    UnitOfMeasure findByDescription(String description);
}
