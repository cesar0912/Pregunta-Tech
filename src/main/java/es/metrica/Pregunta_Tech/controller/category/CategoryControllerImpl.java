package es.metrica.Pregunta_Tech.controller.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.metrica.PreguntaTech.services.category.CategoryServiceInterface;
import es.metrica.Pregunta_Tech.model.Category;

@RestController
@RequestMapping("/categories")
@CrossOrigin("http://localhost:4200/")
public class CategoryControllerImpl implements CategoryControllerInterface {

    CategoryServiceInterface serv;

    @Autowired
    public CategoryControllerImpl(CategoryServiceInterface serv) {
    	super();
        this.serv = serv;
    }

    @Override
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> obtenerCategorias() {
        List<Category> categories = serv.getCategories();
        return ResponseEntity.ok(categories);
    }
}
