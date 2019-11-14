package guru.springframework.recipe.controller;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.service.CategoryService;
import guru.springframework.recipe.service.RecipeService;
import guru.springframework.recipe.service.UnitOfMeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Krzysztof Kukla
 */
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final CategoryService categoryService;
    private final UnitOfMeasureService unitOfMeasureService;
    private final RecipeService recipeService;

    @RequestMapping({"","/","/index"})
    public String index(Model model){
        model.addAttribute("recipes", recipeService.findAll());
        Category category = categoryService.findByDescription("Italian");
        UnitOfMeasure unitOfMeasure = unitOfMeasureService.findByDescription("Cup");
        System.out.println(category);
        System.out.println(unitOfMeasure);
        return "index";
    }
}
