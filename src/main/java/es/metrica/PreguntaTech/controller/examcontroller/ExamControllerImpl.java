package es.metrica.PreguntaTech.controller.examcontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.metrica.PreguntaTech.model.Exam;
import es.metrica.PreguntaTech.services.exam.ExamServices;
@RestController
@RequestMapping("/exam")
@CrossOrigin("http://localhost:4200/")
public class ExamControllerImpl implements ExamController{
	private final ExamServices serv;
	public ExamControllerImpl(ExamServices serv) {
		this.serv = serv;
	}

	@Override
	@PostMapping
	public Exam saveExam(@RequestParam("token") String token, @RequestBody Exam exam ) {
		return serv.saveExam(exam, token);
	}

}
