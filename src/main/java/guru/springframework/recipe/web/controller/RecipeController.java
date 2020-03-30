package guru.springframework.recipe.web.controller;

import guru.springframework.recipe.service.RecipeService;
import guru.springframework.recipe.web.model.RecipeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Krzysztof Kukla
 */
@Controller
@RequestMapping(value = "recipe")
@RequiredArgsConstructor
@Slf4j
public class RecipeController {
    public static final String RECIPE_RECIPE_FORM_URL = "recipe/recipeForm";
    private final RecipeService recipeService;

    private WebDataBinder webDataBinder;

    @InitBinder
    public void initiateBinder(WebDataBinder webDataBinder) {
        this.webDataBinder = webDataBinder;
    }

    @GetMapping("/new")
    public String showById(Model model) {
        model.addAttribute("recipe", new RecipeDto());

        return RECIPE_RECIPE_FORM_URL;
    }

    @GetMapping(value = "/{id}/show")
    public String findById(@PathVariable String id, Model model) {
        //ThymeleafTemplateEngine is gonna handle that publisher - Mono is publisher
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeCommandById(id));
        return RECIPE_RECIPE_FORM_URL;
    }

    @PostMapping
    //@ModelAttribute allows to bind form post parameters to RecipeCommand object
    public String saveOrUpdate(@ModelAttribute("recipe") RecipeDto recipe) {
        webDataBinder.validate();
        BindingResult bindingResult = webDataBinder.getBindingResult();

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return RECIPE_RECIPE_FORM_URL;
        }
        RecipeDto recipeDtoMono = recipeService.saveRecipeCommand(recipe).block();
        return "redirect:/recipe/" + recipeDtoMono.getId() + "/show";
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable String id) {
        log.debug("Deleting id-> " + id);
        recipeService.deleteById(id);
        return "redirect:/";
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NotFoundException.class)
//    public ModelAndView handleException(Exception exception) {
//        log.error("Handling not found exception");
//        log.error(exception.getMessage());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("404error");
//        modelAndView.addObject("exception", exception);
//        return modelAndView;
//    }

}
