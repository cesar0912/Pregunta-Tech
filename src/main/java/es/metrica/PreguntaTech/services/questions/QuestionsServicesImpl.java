package es.metrica.PreguntaTech.services.questions;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import PreguntaTech.utils.model.url.UrlResult;
import es.metrica.PreguntaTech.model.exceptions.InvalidQueryException;
import es.metrica.PreguntaTech.model.exceptions.InvalidUrlException;

@Service
public class QuestionsServicesImpl implements QuestionsServices {

	public QuestionsServicesImpl() {
		super();
	}

	@Override
	public List<Object> getQuestionsFromApi(UrlResult url) {
		try {
			RestTemplate template=new RestTemplate();
			Object[] questions = template.getForObject(url.getUrl(), Object[].class);
			return Arrays.asList(questions);
		} catch (HttpClientErrorException e) {
			throw new InvalidQueryException(e.getMessage());
		}
		catch (RestClientException | IllegalArgumentException r ) {
			throw new InvalidUrlException();
		} 

	}

}
