package reto.tecnico.com.pichincha.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import reto.tecnico.com.pichincha.entity.TipoCambio;

@Repository
public interface ITipoCambioRepository extends ReactiveCrudRepository<TipoCambio, Long> {
	
	@Query("select u from TipoCambio u where u.monedaOrigenCodigo=:codigoori and u.monedaDestinoCodigo=:codigodet")
	Mono<TipoCambio> findById(int codigoori, int codigodet);
	
	Mono<TipoCambio> findByMonedaOrigenCodigoAndMonedaDestinoCodigo(int codigoori, int codigodet);

}
