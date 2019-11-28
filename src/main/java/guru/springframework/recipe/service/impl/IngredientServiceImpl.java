package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.converter.IngredientCommandToIngredient;
import guru.springframework.recipe.converter.IngredientToIngredientCommand;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repository.IngredientRepository;
import guru.springframework.recipe.repository.RecipeRepository;
import guru.springframework.recipe.repository.UnitOfMeasureRepository;
import guru.springframework.recipe.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        log.debug("Filtering ingredient by recipeId and ingredientId");
        Ingredient ingredient = ingredientRepository.findByRecipeIdAndId(recipeId, ingredientId)
            .orElseThrow(() -> new RuntimeException("Ingredient does" + "not exist"));
        return ingredientToIngredientCommand.convert(ingredient);
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Ingredient ingredient;
        Optional<Ingredient> optionalIngredient = ingredientRepository.findByRecipeIdAndId(command.getRecipeId(), command.getId());
        if (optionalIngredient.isPresent()) {
            ingredient = optionalIngredient.get();
            UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.findById(command.getUnitOfMeasure().getId())
                .orElseThrow(() -> new RuntimeException("Unit of measure not found"));
            ingredient.setUnitOfMeasure(unitOfMeasure);
            ingredient.setAmount(command.getAmount());
            ingredient.setDescription(command.getDescription());
        } else {
            ingredient = ingredientCommandToIngredient.convert(command);
        }
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return ingredientToIngredientCommand.convert(savedIngredient);

        // Original from springframework guru
//        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
//
//        if (!recipeOptional.isPresent()) {
//
//            //todo toss error if not found!
//            log.error("Recipe not found for id: " + command.getRecipeId());
//            return new IngredientCommand();
//        } else {
//            Recipe recipe = recipeOptional.get();
//
//            Optional<Ingredient> ingredientOptional = recipe
//                .getIngredients()
//                .stream()
//                .filter(ingredient -> ingredient.getId().equals(command.getId()))
//                .findFirst();
//
//            if (ingredientOptional.isPresent()) {
//                Ingredient ingredientFound = ingredientOptional.get();
//                ingredientFound.setDescription(command.getDescription());
//                ingredientFound.setAmount(command.getAmount());
//                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository.findById(command.getUnitOfMeasure().getId())
//                    .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
//            } else {
//                //add new Ingredient
//                recipe.addIngredient(ingredientCommandToIngredient.convert(command));
//            }
//
//            Recipe savedRecipe = recipeRepository.save(recipe);
//
//            //to do check for fail
//            return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
//                .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
//                .findFirst()
//                .get());
//        }

    }

}
