package es.metrica.Pregunta_Tech.controller.category;


import org.springframework.http.ResponseEntity;
import es.metrica.Pregunta_Tech.model.CategoryResponse;

public interface CategoryControllerInterface {
    ResponseEntity<CategoryResponse> obtenerCategorias();
}
