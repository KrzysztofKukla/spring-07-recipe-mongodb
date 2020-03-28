package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.converter.IngredientCommandToIngredient;
import guru.springframework.recipe.converter.IngredientToIngredientCommand;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repository.reactive.IngredientReactiveRepository;
import guru.springframework.recipe.repository.reactive.UnitOfMeasureReactiveRepository;
import guru.springframework.recipe.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Krzysztof Kukla
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientReactiveRepository ingredientReactiveRepository;
    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Override
    public Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {
        log.debug("Filtering ingredient by recipeId and ingredientId");
        Mono<Ingredient> ingredientMono = getIngredient(recipeId, ingredientId);
        Mono<IngredientCommand> ingredientCommandMono = ingredientMono.map(i -> ingredientToIngredientCommand.convert(i));
        return ingredientCommandMono;
    }

    @Override
    public Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command) {
        Mono<Ingredient> ingredientMono = ingredientReactiveRepository.findByRecipeIdAndId(command.getRecipeId(), command.getId());
        Mono<UnitOfMeasure> unitOfMeasureMono = unitOfMeasureReactiveRepository.findById(command.getUnitOfMeasure().getId());
        Ingredient ingredient = ingredientMono.block();
        ingredient.setUnitOfMeasure(unitOfMeasureMono.block());
        ingredient.setAmount(command.getAmount());
        ingredient.setDescription(command.getDescription());
        Mono<Ingredient> savedIngredientMono = ingredientReactiveRepository.save(ingredient);
        return savedIngredientMono.map((i) -> ingredientToIngredientCommand.convert(i));

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

    @Override
    public Mono<Void> deleteIngredientById(String recipeId, String ingredientId) {
        Mono<Ingredient> ingredientMono = getIngredient(recipeId, ingredientId);
        ingredientReactiveRepository.delete(ingredientMono.block());
        log.debug("Ingredient for {} recipeId has been deleted", recipeId);
        return Mono.empty();
    }

    private Mono<Ingredient> getIngredient(String recipeId, String ingredientId) {
        return ingredientReactiveRepository.findByRecipeIdAndId(recipeId, ingredientId);
    }

}
