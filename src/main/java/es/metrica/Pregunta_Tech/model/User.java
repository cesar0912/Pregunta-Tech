package es.metrica.Pregunta_Tech.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;




@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String surname;

    @Column
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = true)
    private Organization organization;
    // Getters and setters (optional, but recommended for encapsulation)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	public User(Long id, String email, String password, String surname, String name) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.surname = surname;
		this.name = name;
	}
	
	
	public User(String email, String password, String surname, String name) {
		super();
		this.email = email;
		this.password = password;
		this.surname = surname;
		this.name = name;
	}

	public User() {}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(surname, other.surname);
	}
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", email=" + email + ", password=" + password + ", surname=" + surname + ", name="
				+ name + "]";
	}
}
	
	
	
	
	

