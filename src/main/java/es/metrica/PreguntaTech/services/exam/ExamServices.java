package es.metrica.PreguntaTech.services.exam;

import java.util.List;

import es.metrica.PreguntaTech.model.Exam;

public interface ExamServices {

	Exam saveExam(Exam exam, String token);
	
	List<Exam> getExamsUser(String token);
}
