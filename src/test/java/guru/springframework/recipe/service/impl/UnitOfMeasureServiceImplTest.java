package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repository.reactive.UnitOfMeasureReactiveRepository;
import guru.springframework.recipe.web.mapper.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.recipe.web.model.UnitOfMeasureDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class UnitOfMeasureServiceImplTest {

    @Mock
    private UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Mock
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @InjectMocks
    private UnitOfMeasureServiceImpl unitOfMeasureService;

    @Test
    void findByDescription() {
        String description = "description";
        UnitOfMeasure unitOfMeasure = UnitOfMeasure.builder().id("1").description(description).build();
        Mono<UnitOfMeasure> unitOfMeasureMono = Mono.just(unitOfMeasure);

        BDDMockito.when(unitOfMeasureReactiveRepository.findByDescription(description)).thenReturn(unitOfMeasureMono);

        Assertions.assertEquals(description, unitOfMeasureService.findByDescription(description).block().getDescription());
        BDDMockito.then(unitOfMeasureReactiveRepository).should().findByDescription(ArgumentMatchers.anyString());
    }

    @Test
    void findAll() {
        UnitOfMeasure unitOfMeasure1 = UnitOfMeasure.builder().id("1").description("desciption1").build();
        UnitOfMeasure unitOfMeasure2 = UnitOfMeasure.builder().id("2").description("desciption2").build();
        Flux<UnitOfMeasure> unitOfMeasureFlux = Flux.just(unitOfMeasure1, unitOfMeasure2);

        UnitOfMeasureDto measureCommand1 = UnitOfMeasureDto.builder().id("1").description("description1").build();
        UnitOfMeasureDto measureCommand2 = UnitOfMeasureDto.builder().id("2").description("description2").build();

        BDDMockito.when(unitOfMeasureReactiveRepository.findAll()).thenReturn(unitOfMeasureFlux);
        BDDMockito.when(unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure1)).thenReturn(measureCommand1);
        BDDMockito.when(unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure2)).thenReturn(measureCommand2);

        org.assertj.core.api.Assertions.assertThat(unitOfMeasureService.findAllUom().collectList().block())
            .hasSize(unitOfMeasureFlux.collectList().block().size());
        BDDMockito.then(unitOfMeasureReactiveRepository).should().findAll();
        BDDMockito.then(unitOfMeasureToUnitOfMeasureCommand).should(BDDMockito.times(2)).convert(ArgumentMatchers.any(UnitOfMeasure.class));

    }

}