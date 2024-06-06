package es.metrica.PreguntaTech.services.questions;

import java.util.List;

import PreguntaTech.utils.model.url.UrlResult;

public interface QuestionsServices {

	
	List<Object>getQuestionsFromApi(UrlResult url);
}
