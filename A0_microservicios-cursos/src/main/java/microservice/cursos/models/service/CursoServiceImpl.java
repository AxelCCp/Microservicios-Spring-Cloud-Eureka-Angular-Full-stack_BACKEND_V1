package microservice.cursos.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import microservice.commons.services.CommonService;
import microservice.cursos.feign.IFeignClientRespuestas;
import microservice.cursos.models.entity.Curso;
import microservice.cursos.models.repository.ICursoRepository;

@Service
public class CursoServiceImpl extends CommonService<Curso,ICursoRepository> implements ICursoService {

	
	//METODO PERSONALIZADO
	@Override
	@Transactional(readOnly=true)
	public Curso findCursoByAlumnoId(Long id) {
		// TODO Auto-generated method stub
		return repository.findCursoByAlumnoId(id);
	}

	@Override  //LOS METODOS QUE SE CONECTAN A OTRO MICROSERVICIO, NO LLEVAN EL TRANSACTUINAL.  NO SER Q INTERACTUEN CON LA BBDD.
	public Iterable<Long> obtenerExamenesConRespuestasPorAlumno(Long alumnoId) {
		// TODO Auto-generated method stub
		return feignClientRespuestas.obtenerExamenesConRespuestasPorAlumno(alumnoId);
	}

	@Autowired
	private IFeignClientRespuestas feignClientRespuestas;
}
