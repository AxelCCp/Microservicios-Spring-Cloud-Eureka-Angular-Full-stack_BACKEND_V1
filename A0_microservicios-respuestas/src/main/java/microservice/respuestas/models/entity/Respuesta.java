package microservice.respuestas.models.entity;

import microservice.alumnos.commons.models.entity.Alumno;
import microservice.commons.examenes.model.entity.Pregunta;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="respuestas")
public class Respuesta {
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String texto;
	@ManyToOne(fetch=FetchType.LAZY)															//UN ALUMNO PUEDE TENER MUCHAS RESPUESTAS. MIENTRAS Q LAS RESPUESTAS SON DE UN SOLO ALUMNO EN PARTICULAR.
	private Alumno alumno;
	@OneToOne(fetch = FetchType.LAZY)															//LA RELACION ES DE 1 A 1 ENTRE PREGUNTA Y RESPUESTA.
	private Pregunta pregunta;
}
