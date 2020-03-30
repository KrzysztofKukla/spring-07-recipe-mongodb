package guru.springframework.recipe.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.thymeleaf.exceptions.TemplateInputException;

/**
 * @author Krzysztof Kukla
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, TemplateInputException.class})
    public String handleException(Exception exception, Model model) {
        log.error("Handling not found exception {}", exception.getMessage());
        model.addAttribute("exception", exception);
        return "404error";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public String handleWebExchangeBindException(Exception exception, Model model) {
        log.error("Binding exception {}", exception.getMessage());

        return "400error";
    }

}
