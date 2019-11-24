package guru.springframework.recipe.controller;

import guru.springframework.recipe.service.IngredientService;
import guru.springframework.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Krzysztof Kukla
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String ingredientList(@PathVariable Long recipeId, Model model) {

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findRecipeCommandById(recipeId));
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        return "recipe/ingredient/show";
    }

}
