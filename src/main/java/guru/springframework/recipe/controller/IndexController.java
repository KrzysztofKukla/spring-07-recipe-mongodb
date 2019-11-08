package guru.springframework.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Krzysztof Kukla
 */
@Controller
public class IndexController {

    @RequestMapping({"","/","/index"})
    public String index(){
        return "index";
    }
}
