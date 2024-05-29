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
    @Column(name = "id", nullable = false)
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}
	public List<String> getAnswers() {
		return answers;
	}
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	public String getCorrect_Answer() {
		return Correct_Answer;
	}
	public void setCorrect_Answer(String correct_Answer) {
		Correct_Answer = correct_Answer;
	}
	public String getFeedBack() {
		return FeedBack;
	}
	public void setFeedBack(String feedBack) {
		FeedBack = feedBack;
	}
	
}
