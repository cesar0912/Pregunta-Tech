package es.metrica.PreguntaTech.controller.category;


import org.springframework.http.ResponseEntity;

import es.metrica.PreguntaTech.model.CategoryResponse;

public interface CategoryControllerInterface {
    ResponseEntity<CategoryResponse> obtenerCategorias();
}
