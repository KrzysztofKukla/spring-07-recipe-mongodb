package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.repository.CategoryRepository;
import guru.springframework.recipe.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Krzysztof Kukla
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category findByDescription(String description) {
        Optional<Category> byDescription = categoryRepository.findByDescription(description);
        return byDescription.orElseThrow(()->new RuntimeException("Category does not exist"));
    }

}
