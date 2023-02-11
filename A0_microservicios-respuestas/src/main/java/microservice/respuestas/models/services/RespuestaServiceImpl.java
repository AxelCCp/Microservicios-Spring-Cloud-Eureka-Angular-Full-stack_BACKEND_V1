package microservice.respuestas.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import microservice.respuestas.models.entity.Respuesta;
import microservice.respuestas.models.repository.RespuestaRepository;

@Service
public class RespuestaServiceImpl implements IRespuestaService{

	@Override
	@Transactional
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		return respuestaRepository.saveAll(respuestas);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
		// TODO Auto-generated method stub
		return respuestaRepository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
	}

	@Autowired
	private RespuestaRepository respuestaRepository;

	
}
