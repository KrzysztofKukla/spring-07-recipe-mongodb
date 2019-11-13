package guru.springframework.recipe.service;

import guru.springframework.recipe.domain.Category;

/**
 * @author Krzysztof Kukla
 */
public interface CategoryService {
    Category findByDescription(String description);
}
