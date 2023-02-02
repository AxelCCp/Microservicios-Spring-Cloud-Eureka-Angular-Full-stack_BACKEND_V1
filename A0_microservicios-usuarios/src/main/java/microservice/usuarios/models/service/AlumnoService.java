package microservice.usuarios.models.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import microservice.alumnos.commons.models.entity.Alumno;
import microservice.commons.services.CommonService;
import microservice.usuarios.models.repository.IAlumnoRepository;

@Service
public class AlumnoService extends CommonService<Alumno, IAlumnoRepository> implements IAlumnoService{

	//METODO PERSONALIZADO PARA BUSCAR ALUMNOS.
	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String texto) {
		// TODO Auto-generated method stub
		return repository.findByNombreOrApellido(texto);
	}

	
	
}
