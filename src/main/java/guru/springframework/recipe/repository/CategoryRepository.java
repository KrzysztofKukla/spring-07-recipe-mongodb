package guru.springframework.recipe.repository;

import guru.springframework.recipe.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
public interface CategoryRepository extends JpaRepository<Category, String > {
    Optional<Category> findByDescription(String description);
}
