package guru.springframework.recipe.repository;

import guru.springframework.recipe.domain.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Krzysztof Kukla
 */
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {
}
