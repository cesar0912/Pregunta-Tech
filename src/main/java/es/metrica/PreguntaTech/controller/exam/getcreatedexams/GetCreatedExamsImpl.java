package es.metrica.PreguntaTech.controller.exam.getcreatedexams;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.metrica.PreguntaTech.model.Exam;
import es.metrica.PreguntaTech.services.exam.ExamServices;
@RestController
@RequestMapping("/getCreatedExams")
@CrossOrigin("http://localhost:4200/")
public class GetCreatedExamsImpl implements GetCreatedExamsInterface {

	private final ExamServices serv;
	
	
	public GetCreatedExamsImpl(ExamServices serv) {
		super();
		this.serv = serv;
	}

	@GetMapping
	@Override
	public List<Exam> getCreatedExams() {
		return serv.getExams();
	}

	

}
