package guru.springframework.recipe.repository.reactive;

import guru.springframework.recipe.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {
    Mono<Category> findByDescription(String description);
}
