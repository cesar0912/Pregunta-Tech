package es.metrica.PreguntaTech.controller.testcontroller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PreguntaTech.utils.model.url.UrlResult;
import es.metrica.PreguntaTech.services.questions.QuestionsServices;

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
	public List<Object> getTest(@RequestBody UrlResult url) {
		return services.getQuestionsFromApi(url);
	}

}
