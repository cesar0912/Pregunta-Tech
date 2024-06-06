package es.metrica.PreguntaTech.repository.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.metrica.PreguntaTech.model.Exam;

@Repository
public interface ExamRepository  extends JpaRepository<Exam, Long>{

}
