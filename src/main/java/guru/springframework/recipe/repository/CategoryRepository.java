package guru.springframework.recipe.repository;

import guru.springframework.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
public interface CategoryRepository extends CrudRepository<Category, String > {
    Optional<Category> findByDescription(String description);
}
