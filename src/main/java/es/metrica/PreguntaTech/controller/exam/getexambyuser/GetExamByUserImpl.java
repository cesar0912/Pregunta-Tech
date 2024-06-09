package es.metrica.PreguntaTech.controller.exam.getexambyuser;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.metrica.PreguntaTech.model.Exam;
import es.metrica.PreguntaTech.services.exam.ExamServices;

@RestController
@RequestMapping("/getExamsByUser")
@CrossOrigin("http://localhost:4200/")
public class GetExamByUserImpl implements GetExamByUserControllerInterface {

private final	ExamServices serv;

	public GetExamByUserImpl(ExamServices serv) {
		this.serv = serv;
	}
	
	@Override
	@GetMapping()
	public List<Exam> getExamByUser(@RequestHeader("auth") String token) {
		return serv.getExamsUser(token);
	}

	
	

}
