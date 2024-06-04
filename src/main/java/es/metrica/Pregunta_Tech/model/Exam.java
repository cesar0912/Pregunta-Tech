package es.metrica.Pregunta_Tech.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "exam")
public class Exam {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToMany(mappedBy = "examQuestion")
    private List<Questions> questions;
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
