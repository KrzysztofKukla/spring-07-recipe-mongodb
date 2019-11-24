package guru.springframework.recipe.controller;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
@Slf4j
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/new")
    public String showById(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "/recipe/recipeForm";
    }

    @GetMapping(value = "/{id}/show")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeCommandById(id));
        return "/recipe/recipeForm";
    }

    @PostMapping
    //@ModelAttribute allows to bind form post parameters to RecipeCommand object
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedRecipeCommand.getId() + "/show";
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        log.debug("Deleting id-> " + id);
        recipeService.deleteById(id);
        return "redirect:/";
    }

}
