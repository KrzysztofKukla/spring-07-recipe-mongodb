package guru.springframework.recipe.service;

import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.web.model.UnitOfMeasureDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Krzysztof Kukla
 */
public interface UnitOfMeasureService {
    Mono<UnitOfMeasure> findByDescription(String description);

    Flux<UnitOfMeasureDto> findAllUom();

}
