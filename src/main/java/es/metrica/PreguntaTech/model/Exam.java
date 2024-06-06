package es.metrica.PreguntaTech.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "exam")
public class Exam {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany
    @JoinTable(
        name = "questions_exam",
        joinColumns = @JoinColumn(name = "question_id"),
        inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private List<Questions> questions;
	@JsonIgnore
	@ManyToMany(mappedBy = "examUser")
    private List<User> users;
	
	
	public List<Questions> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Exam(Long id, List<Questions> questions, List<User> users) {
		super();
		this.id = id;
		this.questions = questions;
		this.users = users;
	}
	public Exam() {
		super();
	}
	
}
