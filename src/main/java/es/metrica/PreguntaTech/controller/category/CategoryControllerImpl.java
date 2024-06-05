package es.metrica.PreguntaTech.controller.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.metrica.PreguntaTech.model.CategoryResponse;
import es.metrica.PreguntaTech.services.category.CategoryServices;

@RestController
@RequestMapping("/categories")
@CrossOrigin("http://localhost:4200/")
public class CategoryControllerImpl implements CategoryControllerInterface {
  
	
	private final  CategoryServices serv;

    public CategoryControllerImpl(CategoryServices serv) {
    	super();
        this.serv = serv;
    }

    @Override
    @GetMapping()
    public ResponseEntity<CategoryResponse> obtenerCategorias() {
    	CategoryResponse categories = serv.getCategories();
        return ResponseEntity.ok(categories);
    }
}
