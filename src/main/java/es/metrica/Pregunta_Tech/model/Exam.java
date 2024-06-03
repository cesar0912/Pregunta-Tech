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
	@ManyToMany(mappedBy = "exam")
    private List<Questions> questions; 
}
