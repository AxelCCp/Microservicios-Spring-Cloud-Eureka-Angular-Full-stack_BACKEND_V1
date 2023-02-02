package microservice.cursos.models.service;

import microservice.commons.services.ICommonService;
import microservice.cursos.models.entity.Curso;

public interface ICursoService extends ICommonService<Curso>{

	//METODO PERSONALIZADO
	public Curso findCursoByAlumnoId(Long id);
	
}
