package microservice.usuarios.models.service;


import java.util.List;

import microservice.alumnos.commons.models.entity.Alumno;
import microservice.commons.services.ICommonService;

public interface IAlumnoService extends ICommonService<Alumno>{

	//METODO PERSONALIZADO PARA BUSCAR ALUMNOS.
	public List<Alumno>findByNombreOrApellido(String texto); 
	
}
