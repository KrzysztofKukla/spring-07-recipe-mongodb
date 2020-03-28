package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.converter.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repository.UnitOfMeasureRepository;
import guru.springframework.recipe.repository.reactive.UnitOfMeasureReactiveRepository;
import guru.springframework.recipe.service.UnitOfMeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Krzysztof Kukla
 */
@Service
@RequiredArgsConstructor
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Override
    public Mono<UnitOfMeasure> findByDescription(String description) {
       return unitOfMeasureReactiveRepository.findByDescription(description);
    }

    @Override
    public Flux<UnitOfMeasureCommand> findAllUom() {
        return unitOfMeasureReactiveRepository.findAll()
            .map(i->unitOfMeasureToUnitOfMeasureCommand.convert(i));
//        Set<UnitOfMeasureCommand> all = new HashSet<>();
//        unitOfMeasureRepository.findAll().forEach(i -> {
//            all.add(unitOfMeasureToUnitOfMeasureCommand.convert(i));
//        });
//        return all;
    }
}
