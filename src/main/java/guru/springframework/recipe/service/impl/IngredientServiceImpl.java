package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.converter.IngredientToIngredientCommand;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.repository.IngredientRepository;
import guru.springframework.recipe.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Krzysztof Kukla
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        log.debug("Filtering ingredient by recipeId and ingredientId");
        Ingredient ingredient = ingredientRepository.findByRecipeIdAndId(recipeId, ingredientId);
        return ingredientToIngredientCommand.convert(ingredient);
    }

}
