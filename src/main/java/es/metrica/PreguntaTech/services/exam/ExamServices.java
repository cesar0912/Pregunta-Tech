package es.metrica.PreguntaTech.services.exam;

import es.metrica.PreguntaTech.model.Exam;

public interface ExamServices {

	Exam saveExam(Exam exam, String token);
}
