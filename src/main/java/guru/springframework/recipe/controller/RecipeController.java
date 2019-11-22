package guru.springframework.recipe.controller;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Krzysztof Kukla
 */
@Controller
@RequestMapping(value = "/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @RequestMapping(value = "/show/{id}")
    public String findById(Model model, @PathVariable Long id) {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);

        return "recipe/show";
    }

}
