package reto.tecnico.com.pichincha.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reto.tecnico.com.pichincha.entity.Moneda;
import reto.tecnico.com.pichincha.entity.TipoCambio;
import reto.tecnico.com.pichincha.entity.Transaccion;



public interface IMonedaService {

	public Flux<TipoCambio> findAll();
	
	public Mono<Transaccion> change(Transaccion tra, int id);
	
	public Mono<TipoCambio> update(TipoCambio ti,int id);
	
	public Mono<TipoCambio> save(TipoCambio ti, int id);

}
