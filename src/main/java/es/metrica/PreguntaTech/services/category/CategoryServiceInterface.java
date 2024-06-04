package es.metrica.PreguntaTech.services.category;

import java.util.List;

import es.metrica.Pregunta_Tech.model.Category;
import reactor.core.publisher.Mono;

public interface CategoryServiceInterface {
	
    public List<Category> getCategories();

}
