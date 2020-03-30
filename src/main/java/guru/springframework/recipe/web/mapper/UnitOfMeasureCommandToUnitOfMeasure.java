package guru.springframework.recipe.web.mapper;

import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.web.model.UnitOfMeasureDto;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureDto, UnitOfMeasure> {

    //Spring does not guarantee thread safety, so we use here @Synchronized annotation from Lombok to be synchronized and thread safety
    //so can be run in multi threaded environments
    @Synchronized
    //can be null
    @Nullable
    @Override
    public UnitOfMeasure convert(final UnitOfMeasureDto source) {
        if (source == null) {
            return null;
        }

        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(source.getId());
        uom.setDescription(source.getDescription());
        return uom;
    }

}
