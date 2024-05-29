package es.metrica.Pregunta_Tech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.metrica.Pregunta_Tech.repository.Questions.QuestionsRepository;

@Service
public class QuestionsServices {

	private final QuestionsRepository questionsRepository;

	@Autowired
	public QuestionsServices(QuestionsRepository questionsRepository) {
		super();
		this.questionsRepository = questionsRepository;
	}
	
	
}
