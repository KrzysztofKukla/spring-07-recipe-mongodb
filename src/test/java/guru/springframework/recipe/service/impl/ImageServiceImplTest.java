package guru.springframework.recipe.service.impl;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.service.RecipeService;
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

import static org.mockito.ArgumentMatchers.anyLong;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Test
    void saveImageFileTest() throws IOException {
        //given
        Recipe recipe = Recipe.builder().id(1L).build();
        MultipartFile mockMultipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
            "Spring Framework Guru".getBytes());

        BDDMockito.when(recipeService.findById(anyLong())).thenReturn(recipe);
        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        imageService.saveImageFile(1L, mockMultipartFile);

        BDDMockito.then(recipeService).should().findById(anyLong());
        BDDMockito.then(recipeService).should().saveRecipe(recipeArgumentCaptor.capture());
        Recipe savedArgumentCapture = recipeArgumentCaptor.getValue();
        Assertions.assertEquals(mockMultipartFile.getBytes().length, savedArgumentCapture.getImage().length);
    }

}