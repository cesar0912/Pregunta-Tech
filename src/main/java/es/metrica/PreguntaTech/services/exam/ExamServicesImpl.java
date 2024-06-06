package es.metrica.PreguntaTech.services.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import es.metrica.PreguntaTech.model.Exam;
import es.metrica.PreguntaTech.model.User;
import es.metrica.PreguntaTech.repository.exam.ExamRepository;
import es.metrica.PreguntaTech.repository.questions.QuestionsRepository;
import es.metrica.PreguntaTech.repository.user.UserRepository;
import es.metrica.PreguntaTech.utils.jwt.Jwt;

@Service
public class ExamServicesImpl implements ExamServices {
	private final QuestionsRepository questionsRepository;
	private final ExamRepository examRepository;
	private final UserRepository userRepository;
	private final Jwt jwt;
	
	public ExamServicesImpl( Jwt jwt, UserRepository userRepository, ExamRepository examRepository, QuestionsRepository questionsRepository) {
	
		this.questionsRepository = questionsRepository;
		this.examRepository = examRepository;
		this.userRepository = userRepository;
		this.jwt = jwt;
	}

	@Override
    @Transactional
	public Exam saveExam(Exam exam, String token) {


		Optional<User> userOpt=userRepository.findById(jwt.getUser(token));

		if(userOpt.isPresent()) {
			User user=userOpt.get();
			List<Exam> examsUser=new ArrayList();
			if(user.getExamUser()!=null) {
				examsUser.addAll(user.getExamUser());
			}
		
			exam.setQuestions(questionsRepository.saveAll(exam.getQuestions()));
			
			examsUser.add(examRepository.save(exam));
			user.setExamUser(examsUser);
		
			return exam;

		
		}
		return null;
	}

	@Override
	public List<Exam> getExamsUser(String token) {
		Optional<User> userOpt=userRepository.findById(1L);
		if(userOpt.isPresent()) {
			return userOpt.get().getExamUser();
			
			
		}

		return new ArrayList();
	}

	@Override
	public List<Exam> getExams() {
		return examRepository.findAll();
	}

}
	

