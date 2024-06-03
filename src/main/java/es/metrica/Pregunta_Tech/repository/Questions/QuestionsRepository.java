package es.metrica.Pregunta_Tech.repository.Questions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.metrica.Pregunta_Tech.model.Questions;
@Repository
public interface QuestionsRepository  extends JpaRepository<Questions, Long>{

}
