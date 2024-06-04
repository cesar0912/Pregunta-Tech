package es.metrica.Pregunta_Tech.controller.testcontroller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import es.metrica.Pregunta_Tech.services.questions.QuestionsServices;

@RestController
@RequestMapping("/test")
@CrossOrigin("http://localhost:4200/")
public class TestControllerImpl implements TestControllerInterface {

	QuestionsServices services;
	 
	public TestControllerImpl(QuestionsServices services) {
		this.services=services;
	}

	
	
	@Override
	@PostMapping()
	public List<Object> getTest(@RequestBody String url) {
		return services.getQuestionsFromApi(url);
	}
	

}
