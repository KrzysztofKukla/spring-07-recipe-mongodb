package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repository.UnitOfMeasureRepository;
import guru.springframework.recipe.service.UnitOfMeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
@Service
@RequiredArgsConstructor
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    public UnitOfMeasure findByDescription(String description) {
        Optional<UnitOfMeasure> byDescription = unitOfMeasureRepository.findByDescription(description);
        return byDescription.orElseThrow(()->new RuntimeException("Unit of measure does not exist"));
    }

}
