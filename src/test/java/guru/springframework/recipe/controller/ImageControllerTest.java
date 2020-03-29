package guru.springframework.recipe.controller;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.controller.exceptionhandler.ControllerExceptionHandler;
import guru.springframework.recipe.service.ImageService;
import guru.springframework.recipe.service.RecipeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author Krzysztof Kukla
 */
@ExtendWith(MockitoExtension.class)
class ImageControllerTest {

    @Mock
    private ImageService imageService;

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private ImageController imageController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(imageController)
            .setControllerAdvice(ControllerExceptionHandler.class)
            .build();
    }

    @Test
    void showUploadFormTest() throws Exception {
        //given
        RecipeCommand recipeCommand = RecipeCommand.builder().id("1").description("description").build();

        //when
        BDDMockito.when(recipeService.findRecipeCommandById(ArgumentMatchers.anyString())).thenReturn(Mono.just(recipeCommand));

        mockMvc.perform(get("/recipe/1/image"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("recipe", Matchers.any(RecipeCommand.class)))
            .andExpect(view().name("recipe/imageuploadform"));
        BDDMockito.then(recipeService).should().findRecipeCommandById(anyString());
    }

    @Test
    void handleImagePostTest() throws Exception {
        //given
        MockMultipartFile mockMultipartFile =
            new MockMultipartFile("imagefile", "testing.txt", "text/plain", "Spring framework guru".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/recipe/1/image").file(mockMultipartFile))
            .andExpect(status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/1/show"))

            //it tells what should be consistent in header in url
            .andExpect(MockMvcResultMatchers.header().string("Location", "/recipe/1/show"));

        BDDMockito.then(imageService).should().saveImageFile(anyString(), any(MultipartFile.class));

    }

    @Test
    void renderImageFromDbTest() throws Exception {

        //given
        RecipeCommand command = new RecipeCommand();
        command.setId("1");

        String s = "fake image text";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;

        for (byte primByte : s.getBytes()) {
            bytesBoxed[i++] = primByte;
        }

        command.setImage(bytesBoxed);

        BDDMockito.when(recipeService.findRecipeCommandById(anyString())).thenReturn(Mono.just(command));

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
            .andExpect(status().isOk())
            .andReturn().getResponse();

        byte[] reponseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length, reponseBytes.length);
    }

    @Test
    void invalidUrlException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recaape"))
            .andExpect(status().isNotFound());
//            .andExpect(view().name("404error"));
    }

}