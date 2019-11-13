package guru.springframework.recipe.controller;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.service.CategoryService;
import guru.springframework.recipe.service.UnitOfMeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Krzysztof Kukla
 */
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final CategoryService categoryService;
    private final UnitOfMeasureService unitOfMeasureService;

    @RequestMapping({"","/","/index"})
    public String index(){
        Category category = categoryService.findByDescription("Italian");
        UnitOfMeasure unitOfMeasure = unitOfMeasureService.findByDescription("Cupa");
        System.out.println(category);
        System.out.println(unitOfMeasure);
        return "index";
    }
}
