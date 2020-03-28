package guru.springframework.recipe.service;

import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.domain.UnitOfMeasure;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
public interface UnitOfMeasureService {
    Mono<UnitOfMeasure> findByDescription(String description);

    Flux<UnitOfMeasureCommand> findAllUom();

}
