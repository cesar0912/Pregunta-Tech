package es.metrica.PreguntaTech.services.category;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import es.metrica.Pregunta_Tech.model.Category;

@Service
public class CategoryServiceImpl implements CategoryServiceInterface {

    @Autowired
    public CategoryServiceImpl() {
    	super();
    }

    @Override
    public List<Category> getCategories() {
    	RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://www.preguntapi.dev/api/categories";
        Category[] categories = restTemplate.getForObject(apiUrl, Category[].class);
        return List.of(categories);
    }
}
