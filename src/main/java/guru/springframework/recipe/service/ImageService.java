package guru.springframework.recipe.service;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

/**
 * @author Krzysztof Kukla
 */
public interface ImageService {
    Mono<Void> saveImageFile(String id, MultipartFile file);

}
