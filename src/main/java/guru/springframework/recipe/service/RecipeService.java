package guru.springframework.recipe.service;

import guru.springframework.recipe.domain.Recipe;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
public interface RecipeService {
    Set<Recipe> findAll();
    void saveAll(Collection<Recipe> recipes);
}
