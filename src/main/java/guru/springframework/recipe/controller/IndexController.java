package guru.springframework.recipe.controller;

import guru.springframework.recipe.service.CategoryService;
import guru.springframework.recipe.service.RecipeService;
import guru.springframework.recipe.service.UnitOfMeasureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Krzysztof Kukla
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final CategoryService categoryService;
    private final UnitOfMeasureService unitOfMeasureService;
    private final RecipeService recipeService;

    @RequestMapping({"","/","/index"})
    public String index(Model model){
        //returning back to Flux for WebFlux Thymeleaf
        //when Thymeleaf template picks that up and renders that,
        // then Flux will trigger stream a data from Mongo into Thymeleaf template
        model.addAttribute("recipes", recipeService.findAll());
        return "index";
    }
}
