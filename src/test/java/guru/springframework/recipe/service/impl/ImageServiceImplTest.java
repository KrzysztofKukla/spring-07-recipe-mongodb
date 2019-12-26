package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Test
    void saveImageFileTest() throws IOException {
        //given
        Recipe recipe = Recipe.builder().id("1").build();
        MultipartFile mockMultipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
            "Spring Framework Guru".getBytes());

        BDDMockito.when(recipeRepository.findById(anyString())).thenReturn(Optional.of(recipe));
        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        imageService.saveImageFile("1", mockMultipartFile);

        BDDMockito.then(recipeRepository).should().findById(anyString());
        BDDMockito.then(recipeRepository).should().save(recipeArgumentCaptor.capture());
        Recipe savedArgumentCapture = recipeArgumentCaptor.getValue();
        Assertions.assertEquals(mockMultipartFile.getBytes().length, savedArgumentCapture.getImage().length);
    }

}