package reto.tecnico.com.pichincha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reto.tecnico.com.pichincha.entity.RegistroEventos;
import reto.tecnico.com.pichincha.repository.IRegistroEventosRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistroEventosServiceImpl implements IRegistroEventosService {
	
	@Autowired
	private IRegistroEventosRepository registroSerive;

	@Override
	@Transactional(readOnly = true)
	public Flux<RegistroEventos> findAll() {

		return registroSerive.findAll();
	}

}
