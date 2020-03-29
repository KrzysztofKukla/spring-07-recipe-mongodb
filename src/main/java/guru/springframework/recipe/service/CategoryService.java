package guru.springframework.recipe.service;

import guru.springframework.recipe.domain.Category;
import reactor.core.publisher.Mono;

/**
 * @author Krzysztof Kukla
 */
public interface CategoryService {
    Mono<Category> findByDescription(String description);
}
