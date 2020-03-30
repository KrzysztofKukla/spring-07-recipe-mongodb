package guru.springframework.recipe.web.mapper;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.web.model.CategoryDto;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryDto> {

    @Synchronized
    @Nullable
    @Override
    public CategoryDto convert(Category source) {
        if (source == null) {
            return null;
        }

        final CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(source.getId());
        categoryDto.setDescription(source.getDescription());

        return categoryDto;
    }

}
