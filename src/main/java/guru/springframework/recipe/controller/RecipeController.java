package guru.springframework.recipe.controller;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String findById(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);

        return "recipe/show";
    }

    @RequestMapping("/new")
    public String showById(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "/recipe/recipeForm";
    }

    @PostMapping
    //@ModelAttribute allows to bind form post parameters to RecipeCommand object
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/show/" + savedRecipeCommand.getId();
    }

}
