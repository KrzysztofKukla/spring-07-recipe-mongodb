package guru.springframework.recipe.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Krzysztof Kukla
 */
public interface ImageService {
    void saveImageFile(Long id, MultipartFile file);

}
