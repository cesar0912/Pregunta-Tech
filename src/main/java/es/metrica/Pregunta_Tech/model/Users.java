package es.metrica.Pregunta_Tech.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column
	String email;
	@Column
	String password;
	@Column
	String surname;
	@Column
	String name;
	
	
	/* TODO
	@ManyToMany(mappedBy = "users")
	...
	List<Exam> done;
	@ManyToOne
	Long idGrupo
	*/
	public Users(Long id, String email, String password, String surname, String name) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.surname = surname;
		this.name = name;
	}
	
	public Users() {}
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", email=" + email + ", password=" + password + ", surname=" + surname + ", name="
				+ name + "]";
	}
	
	
}
