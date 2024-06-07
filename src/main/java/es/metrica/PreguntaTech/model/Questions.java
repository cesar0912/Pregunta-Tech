package es.metrica.PreguntaTech.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
	@Column
	private String category;
	
	@Column
	private String level;
	
	@Column
	private String question;
	
	@Column
	private List<String> answers;
	
	@Column(name="correct_answer")
	@JsonProperty("correct_answer")
	private String correctAnswer;
	
	@Column
	private String feedback;
	
	@ManyToMany(mappedBy = "questions")
    private List<Exam> examQuestion;
	public Questions() {}
	public Questions(Long id, String category, String level, String question, List<String> answers,
			String correctAnswer, String feedBack) {
		super();
		this.id = id;
		this.category = category;
		this.level = level;
		this.question = question;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
		feedback = feedBack;
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
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<String> getAnswers() {
		return answers;
	}
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getFeedBack() {
		return feedback;
	}
	public void setFeedBack(String feedBack) {
		feedback = feedBack;
	}
	public Questions(String category, String level, String question, List<String> answers, String correctAnswer,
			String feedback) {
		super();
		this.category = category;
		this.level = level;
		this.question = question;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
		this.feedback = feedback;
	}
	
	
}
