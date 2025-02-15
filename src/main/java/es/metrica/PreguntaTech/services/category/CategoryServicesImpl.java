package es.metrica.PreguntaTech.services.category;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.metrica.PreguntaTech.model.CategoryResponse;
@Service
public class CategoryServicesImpl implements CategoryServices{

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
