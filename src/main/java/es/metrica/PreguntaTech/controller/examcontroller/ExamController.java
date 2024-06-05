package es.metrica.PreguntaTech.controller.examcontroller;

import es.metrica.PreguntaTech.model.Exam;

public interface ExamController {

	Exam saveExam(String token,Exam exam);
}
