package microservice.respuestas.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import microservice.respuestas.models.entity.Respuesta;

public interface RespuestaRepository extends CrudRepository<Respuesta,Long>{

	//SABER QUE PREGUNTAS FUERON RESPONDIDAS UN ALUMNO EN PARTICULAR Y EN UN EXAMEN EN PARTICULAR.
	//JOIN : ES PARA Q EN UNA SOLA CONSULTA TRAIGA TODOS LOS OBJETOS RELACIONADOS. ES DECIR, PARA QUE RETORNE LA RESPUESTA, CON EL ALUMNO Y TAMBN LA PREGUNTA. Y Q LA PREGUNTA TAMBN TRAIGA EL EXAMEN.
	
	//AQUÍ HAY TRES OBJ QUE SE SON RELACIONADOS. "a" , "p" , "e".
	//"e" ESTÁ DENTRO DE "p" POR LA RELACIÓN ENTRE TABLAS.
	//"p" Y "a" ESTÁN DENTRO DE "r".
	//POR LO TANTO FETCH LO QUE HACE ES DEVOLVER TODOS ESTOS OBJETOS RELACIONADOS EN UNA SOLA CONSULTA.
	
	//WHERE : ES LA CONDICIÓN. EN ESTE CASO DICE QUE: TRAER SOLAMENTE EL ALUMNO CON EL ID X Y EL EXAMEN CON EL ID X.
	
	@Query("select r from Respuesta r join fetch r.alumno a join fetch r.pregunta p join fetch p.examen e where a.id=?1 and e.id=?2")
	public Iterable<Respuesta>findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	
	
}
