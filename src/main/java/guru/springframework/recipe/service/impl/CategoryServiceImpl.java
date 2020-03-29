package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.repository.CategoryRepository;
import guru.springframework.recipe.repository.reactive.CategoryReactiveRepository;
import guru.springframework.recipe.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryReactiveRepository categoryReactiveRepository;

    @Override
    public Mono<Category> findByDescription(String description) {
        return categoryReactiveRepository.findByDescription(description);
    }

}
