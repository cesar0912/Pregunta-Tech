package es.metrica.Pregunta_Tech.services.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.metrica.Pregunta_Tech.model.Category;
import es.metrica.Pregunta_Tech.model.CategoryResponse;
@Service
public class CategoryServicesImpl implements CategoryServices{

	@Autowired
	public CategoryServicesImpl() {
	super();
	}

	@Override
	public CategoryResponse getCategories() {
		RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://www.preguntapi.dev/api/categories";
        CategoryResponse category = restTemplate.getForObject(apiUrl, CategoryResponse.class);
        return category;
	}

	
}
