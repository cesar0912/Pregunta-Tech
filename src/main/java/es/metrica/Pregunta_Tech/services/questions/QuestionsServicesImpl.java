package es.metrica.Pregunta_Tech.services.questions;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import es.metrica.Pregunta_Tech.model.exceptions.InvalidQueryException;
import es.metrica.Pregunta_Tech.model.exceptions.InvalidUrlException;
import es.metrica.Pregunta_Tech.repository.Questions.QuestionsRepository;

@Service
public class QuestionsServicesImpl implements QuestionsServices {

	private final QuestionsRepository questionsRepository;

	@Autowired
	public QuestionsServicesImpl(QuestionsRepository questionsRepository) {
		super();
		this.questionsRepository = questionsRepository;
	}

	@Override
	public List<Object> getQuestionsFromApi(String url) {
		try {
			RestTemplate template=new RestTemplate();
			Object[] questions = template.getForObject(url, Object[].class);
			return Arrays.asList(questions);
		} catch (HttpClientErrorException e) {
			throw new InvalidQueryException(e.getMessage());
		}
		catch (RestClientException | IllegalArgumentException r ) {
			throw new InvalidUrlException();
		} 

	}

}
