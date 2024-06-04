package es.metrica.Pregunta_Tech.controller.category;

import java.util.List;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import es.metrica.Pregunta_Tech.model.Category;
import es.metrica.Pregunta_Tech.model.CategoryResponse;

public interface CategoryControllerInterface {
    ResponseEntity<CategoryResponse> obtenerCategorias();
}
