package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repository.RecipeRepository;
import guru.springframework.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe does not exist"));
    }

    @Override
    public Set<Recipe> findAll() {
        return new HashSet<>(recipeRepository.findAll());
    }

    @Override
    public void saveAll(Collection<Recipe> recipes) {
        recipeRepository.saveAll(recipes);
    }

}
