package microservice.cursos.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import microservice.alumnos.commons.models.entity.Alumno;
import microservice.commons.controllers.CommonController;
import microservice.commons.examenes.model.entity.Examen;
import microservice.cursos.models.entity.Curso;
import microservice.cursos.models.service.ICursoService;

@RestController
public class CursoController extends CommonController<Curso,ICursoService>{
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?>editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Curso>o = this.service.porId(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursodb = o.get();
		cursodb.setNombre(curso.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursodb));
	}
	
	
	//DOS METODOS DEL TIPO  PUT QUE SON PARA MODIFICAR EL CURSO. PARA ASIGNAR ALUMNOS Y ELIMINAR ALUMNOS.
	
	//RECIBE POR EL CUERPO DEL REQUEST, UN ARREGLO DE ALUMNOS.
	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?>asignarAlumno(@RequestBody List<Alumno>alumnos, @PathVariable Long id){
		Optional<Curso>o = this.service.porId(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursodb = o.get();
		alumnos.forEach(a -> {
			cursodb.addAlumnos(a);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursodb));
	}
	
	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?>eliminarAlumnos(@RequestBody Alumno alumno, @PathVariable Long id){
		Optional<Curso>o = this.service.porId(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursodb = o.get();
		cursodb.removeAlumno(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursodb));
	}
	
	
	//DOS METODOS DEL TIPO  PUT QUE SON PARA MODIFICAR EL CURSO SEGÚN LOS EXAMENES Q TIENE. PARA ASIGNAR EXAMENES Y ELIMINAR EXAMENES AL CURSO.
	
	@PutMapping("/{id}/asignar-examenes")
	public ResponseEntity<?>asignarExamenes(@RequestBody List<Examen>examenes, @PathVariable Long id){
		Optional<Curso>o = this.service.porId(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursodb = o.get();
		examenes.forEach(a -> {
			cursodb.addExamen(a);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursodb));
	}
	
	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?>eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){
		Optional<Curso>o = this.service.porId(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursodb = o.get();
		cursodb.removeExamen(examen);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursodb));
	}
	
	
	
	//ENCUENTRA UN CURSO SEGÚN EL ID DEL ALUMNO.
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?>buscarAlumnoporId(@PathVariable long id){
		Curso curso = service.findCursoByAlumnoId(id);
		
		
		if(curso != null) {
			//OBTIENE LOS EXAMENES RESPONDIDOS POR EL ALUMNO
			List<Long>examenesIds = (List<Long>)service.obtenerExamenesConRespuestasPorAlumno(id);
			//SE OBTIENEN TODOS LOS EXAMENES Y SE SETTEAN EN TRUE LOS EXAMENES RESPONDIDOS
			List<Examen>examenes = curso.getExamenes().stream().map(examen -> {
				if(examenesIds.contains(examen.getId())) {
					examen.setRespondido(true);
				}
				return examen;
			}).collect(Collectors.toList());
			//ACTUALIZA LA LISTA
			curso.setExamenes(examenes);
		}
		
		
		return ResponseEntity.ok(curso);
	}
	
	
	
		
	
}
