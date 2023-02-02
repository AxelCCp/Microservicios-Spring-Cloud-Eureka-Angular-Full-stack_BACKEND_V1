package microservice.cursos.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import microservice.cursos.models.entity.Curso;

//46 : SE CAMBIA CRUDREPOSITORY POR PAGING AND SORTING PARA PAGINACION.
public interface ICursoRepository extends PagingAndSortingRepository<Curso,Long>{

	//31
	//METODO PERSONALIZADO QUE DEVUELVE EL CURSO DE UN ALUMNO SEGÚN EL ID DEL ALUMNO.
	//FETCH : ES PARA POBLAR EL CURSO CON LA LISTA DE ALUMNOS.
	@Query("select c from Curso c  join fetch c.alumnos a where a.id=?1")
	public Curso findCursoByAlumnoId(Long id);
	
	
}
