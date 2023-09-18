package reto.tecnico.com.pichincha.service;

import reactor.core.publisher.Flux;
import reto.tecnico.com.pichincha.entity.RegistroEventos;

public interface IRegistroEventosService {
	
	public Flux<RegistroEventos> findAll();

}
