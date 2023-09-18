package reto.tecnico.com.pichincha.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import reto.tecnico.com.pichincha.entity.Moneda;
import reto.tecnico.com.pichincha.entity.TipoCambio;

@Repository
public interface IMonedaRepository extends ReactiveCrudRepository<Moneda, Long>{
	
	@Query("select u from Moneda u where u.id=:codigoori or u.id=:codigodet")
	Mono<Moneda> findById(Long codigoori, Long codigodet);
}