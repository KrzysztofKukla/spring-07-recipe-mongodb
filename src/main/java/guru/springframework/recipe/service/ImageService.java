package guru.springframework.recipe.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Krzysztof Kukla
 */
public interface ImageService {
    void saveImageFile(String id, MultipartFile file);

}
