package es.metrica.Pregunta_Tech.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="questions")
public class Questions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column
	String category;
	@Column
	String Level;
	@Column
	String Question;
	@Column
	List<String> answers;
	@Column
	String Correct_Answer;
	@Column
	String FeedBack;
	public Questions(Long id, String category, String level, String question, List<String> answers,
			String correct_Answer, String feedBack) {
		super();
		this.id = id;
		this.category = category;
		Level = level;
		Question = question;
		this.answers = answers;
		Correct_Answer = correct_Answer;
		FeedBack = feedBack;
	}
	
}
