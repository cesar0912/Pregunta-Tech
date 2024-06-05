package es.metrica.PreguntaTech.repository.Questions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.metrica.PreguntaTech.model.Questions;
@Repository
public interface QuestionsRepository  extends JpaRepository<Questions, Long>{

}
