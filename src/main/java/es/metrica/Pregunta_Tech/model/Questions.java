package es.metrica.Pregunta_Tech.model;

import java.util.List;
//TODO refactor

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
	private String correctAnswer;
	
	@Column
	private String FeedBack;
	
	@ManyToMany
    @JoinTable(
        name = "questions_exam",
        joinColumns = @JoinColumn(name = "exam_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Exam> examQuestion;
	public Questions() {}
	public Questions(Long id, String category, String level, String question, List<String> answers,
			String correct_Answer, String feedBack) {
		super();
		this.id = id;
		this.category = category;
		level = level;
		question = question;
		this.answers = answers;
		correctAnswer = correct_Answer;
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
		return level;
	}
	public void setLevel(String level) {
		level = level;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		question = question;
	}
	public List<String> getAnswers() {
		return answers;
	}
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	public String getCorrect_Answer() {
		return correctAnswer;
	}
	public void setCorrect_Answer(String correct_Answer) {
		correctAnswer = correct_Answer;
	}
	public String getFeedBack() {
		return FeedBack;
	}
	public void setFeedBack(String feedBack) {
		FeedBack = feedBack;
	}
	
}
