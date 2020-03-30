package guru.springframework.recipe.web.controller;

import guru.springframework.recipe.service.IngredientService;
import guru.springframework.recipe.service.RecipeService;
import guru.springframework.recipe.service.UnitOfMeasureService;
import guru.springframework.recipe.web.model.IngredientDto;
import guru.springframework.recipe.web.model.UnitOfMeasureDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Flux;

/**
 * @author Krzysztof Kukla
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class IngredientController {
    private static final String INGREDIENT_FORM_URL = "recipe/ingredient/ingredientform";
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    private WebDataBinder webDataBinder;

    @InitBinder("ingredient")
    public void initDataBinder(WebDataBinder webDataBinder) {
        this.webDataBinder = webDataBinder;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String ingredientList(@PathVariable String recipeId, Model model) {

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findRecipeCommandById(recipeId));
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String ingredientId,
                                         Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId).block());

        return "recipe/ingredient/ingredientform";
    }

    //with every request this is gonna be bound with  model.addAttribute("uomList", unitOfMeasureService.findAllUom());
    @ModelAttribute("uomList")
    public Flux<UnitOfMeasureDto> populateUomList() {
        return unitOfMeasureService.findAllUom();
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute("ingredient") IngredientDto ingredient, @PathVariable String recipeId, Model model) {

        webDataBinder.validate();
        BindingResult bindingResult = webDataBinder.getBindingResult();

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                log.debug(error.toString());
            });
//            model.addAttribute("uomList", unitOfMeasureService.findAllUom()); this will be bound with @ModelAttribute("uomList")
            return INGREDIENT_FORM_URL;
        }

        IngredientDto savedCommand = ingredientService.saveIngredientCommand(ingredient).block();
        log.debug("Saved recipeId-> " + savedCommand.getRecipeId());
        log.debug("Saved ingredientId-> " + savedCommand.getId());
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model) {
        IngredientDto newIngredientDto = IngredientDto.builder().recipeId(recipeId).unitOfMeasure(new UnitOfMeasureDto()).build();
        model.addAttribute("ingredient", newIngredientDto);
//        model.addAttribute("uomList", unitOfMeasureService.findAllUom()); this will be bound with @ModelAttribute("uomList")

        return "recipe/ingredient/ingredientform";
    }

    @DeleteMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String ingredientId) {
        log.debug("Deleting ingredient id -> {} ", ingredientId);
        ingredientService.deleteIngredientById(recipeId, ingredientId);
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

}
