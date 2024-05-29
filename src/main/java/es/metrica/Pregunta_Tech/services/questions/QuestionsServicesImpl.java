package es.metrica.Pregunta_Tech.services.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.metrica.Pregunta_Tech.repository.Questions.QuestionsRepository;

@Service
public class QuestionsServicesImpl implements QuestionsServices {

	private final QuestionsRepository questionsRepository;

	@Autowired
	public QuestionsServicesImpl(QuestionsRepository questionsRepository) {
		super();
		this.questionsRepository = questionsRepository;
	}
	
	
}
