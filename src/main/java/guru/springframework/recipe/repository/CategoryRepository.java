package guru.springframework.recipe.repository;

import guru.springframework.recipe.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Krzysztof Kukla
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
