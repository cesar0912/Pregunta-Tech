package es.metrica.PreguntaTech.controller.exam.getexambyuser;

import java.util.List;

import es.metrica.PreguntaTech.model.Exam;

public interface GetExamByUserControllerInterface {
	List<Exam> getExamByUser(String token);
}
