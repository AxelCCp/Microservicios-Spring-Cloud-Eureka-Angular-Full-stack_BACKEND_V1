package microservice.cursos.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import microservice.alumnos.commons.models.entity.Alumno;
import microservice.commons.examenes.model.entity.Examen;

@Entity
@Table(name="cursos")
public class Curso implements Serializable {

	private static final long serialVersionUID = -3415825813094397347L;
	
	public Curso() {
		this.alumnos = new ArrayList<>();
		this.examenes = new ArrayList<>();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	
	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	
	//PARA AGREGAR ALUMNOS A LA LISTA
	public void addAlumnos(Alumno alumno) {
		this.alumnos.add(alumno);
	}
	
	//PARA ELIMINAR ALUMNOS A LA LISTA
	public void removeAlumno(Alumno alumno) {
		this.alumnos.remove(alumno);
	}
	

	public List<Examen> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<Examen> examenes) {
		this.examenes = examenes;
	}
	
	//PARA AGREGAR EXAMENES AL CURSO.
	public void addExamen(Examen examen) {
		this.examenes.add(examen);
	}
	
	//PARA QUITAR EXAMENES DEL CURSO  
	public void removeExamen(Examen examen) {
		this.examenes.remove(examen);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String nombre;
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@OneToMany(fetch = FetchType.LAZY)  //RELACI??N DE UN CURSO A VARIOS ALUMNOS.
	private List<Alumno>alumnos;
	@ManyToMany							//UN CURSO PUEDE TENER VARIOS EXAMENES Y A LA VEZ UN EXAMEN PUEDE ESTAR EN DIFERENTES CURSOS.
	private List<Examen>examenes;
}
