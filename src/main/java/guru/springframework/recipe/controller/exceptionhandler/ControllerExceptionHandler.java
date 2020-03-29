package guru.springframework.recipe.controller.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author Krzysztof Kukla
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleNumberFormat(Exception exception) {
//        log.error("Wrong Url address-> " + exception.getMessage());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("404error");
//        modelAndView.addObject("exception", exception);
//        return modelAndView;
//
//    }

}
