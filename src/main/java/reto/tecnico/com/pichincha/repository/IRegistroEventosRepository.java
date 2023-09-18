package reto.tecnico.com.pichincha.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reto.tecnico.com.pichincha.entity.RegistroEventos;

@Repository
public interface IRegistroEventosRepository extends ReactiveCrudRepository<RegistroEventos, Integer> {
	


}
