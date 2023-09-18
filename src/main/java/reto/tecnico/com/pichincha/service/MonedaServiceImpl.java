package reto.tecnico.com.pichincha.service;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reto.tecnico.com.pichincha.entity.Moneda;
import reto.tecnico.com.pichincha.entity.RegistroEventos;
import reto.tecnico.com.pichincha.entity.TipoCambio;
import reto.tecnico.com.pichincha.entity.Transaccion;
import reto.tecnico.com.pichincha.exception.CustomException;
import reto.tecnico.com.pichincha.repository.IMonedaRepository;
import reto.tecnico.com.pichincha.repository.IRegistroEventosRepository;
import reto.tecnico.com.pichincha.repository.ITipoCambioRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class MonedaServiceImpl implements IMonedaService {
	
	private final static String NF_MESSAGE = "moneda not found";
    private final static String NAME_MESSAGE = "moneda name already in use";

    @Autowired
	private IMonedaRepository monedaRepository;
	
	@Autowired
	private ITipoCambioRepository tipoCambioDao;
	
	@Autowired
	private IRegistroEventosRepository registrooDao;
	
	@Override
	@Transactional(readOnly = true)
	public Flux<TipoCambio> findAll() {
		return tipoCambioDao.findAll();
	}
	
	
	@Override
	public Mono<Transaccion> change(Transaccion tra,int id) {
		Mono<TipoCambio> tip= tipoCambioDao.findById(tra.getMoneda_origen(),tra.getMoneda_destino())
				.switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, NF_MESSAGE)));
		Transaccion t=new Transaccion();
		Mono<Transaccion> result=Mono.just(t).map(x->{
			Mono<BigDecimal> montoResult = tip.flatMap(p->{return Mono.just(p.getValor().multiply(tra.getMonto()));});
			x.setMoneda_destino(tra.getMoneda_destino());
			x.setMoneda_origen(tra.getMoneda_origen());
			x.setMonto(tra.getMonto());
			x.setMoneda_destino(tra.getMoneda_destino());
			montoResult.subscribe(m->x.setMonto_tipo_cambio(m));
			return x;
			});
		tip.hasElement().hasElement().subscribe(val-> {
			 if(val) {
				 tip.subscribe(x->this.audit(x.getId().intValue(),id,"busquedaTipoCambio")); 
				 }});
		return result;
	}
	
	@Override
	public Mono<TipoCambio> save(TipoCambio ti, int id) {
		Mono<Boolean> existsids =monedaRepository.findById(Long.valueOf(ti.getMonedaOrigenCodigo()),Long.valueOf(ti.getMonedaDestinoCodigo())).hasElement();
		
		existsids.subscribe(System.out::println);
		Mono<TipoCambio> saves =existsids.flatMap(exis->{
			if(!exis) {
				return Mono.error(new CustomException(HttpStatus.NOT_FOUND, NF_MESSAGE));
			}else{
				return findIdsTipoCambio(ti);}}
			);
//		saves.hasElement().subscribe(val-> {
//			 if(val) {
//				 saves.subscribe(x->this.audit(x.getId().intValue(),id,"saveTipoCambio")); 
//				 }});
		
		return saves;   
	}
	
	private Mono<TipoCambio> findIdsTipoCambio(TipoCambio ti){
		Mono<Boolean> existsids =tipoCambioDao.findById(ti.getMonedaOrigenCodigo(),ti.getMonedaDestinoCodigo()).hasElement();
		Mono<TipoCambio> save =existsids.flatMap(exis->{
			if(exis) {
				return Mono.error(new CustomException(HttpStatus.NOT_FOUND, NF_MESSAGE));
			}else{
				return tipoCambioDao.save(ti);}}
			);
		
		return save; 
	}
	
	@Override
	public Mono<TipoCambio> update(TipoCambio ti, int id) {
		Mono<TipoCambio> tipoCambioActual = tipoCambioDao.findById(ti.getMonedaOrigenCodigo(),ti.getMonedaDestinoCodigo());
		Mono<Boolean> tip=tipoCambioActual.hasElement();
		 Mono<TipoCambio> tipoCambioChange = tip.flatMap(existsId->existsId ?
				tipoCambioActual.flatMap(
						existingTipoCambio -> {existingTipoCambio.setValor(ti.getValor());
												return tipoCambioDao.save(existingTipoCambio);
												})
                		: Mono.error(new CustomException(HttpStatus.NOT_FOUND, NF_MESSAGE)));

		 tipoCambioChange.hasElement().subscribe(val-> {
			 if(val) {
				 tipoCambioActual.subscribe(x->this.audit(x.getId().intValue(),id,"updateTipoCambio")); 
				 }});
			 
		 return tipoCambioChange;	  
        
	}
	
	private void audit (int idtipocambio, int idUser,String tipo) {
		RegistroEventos regevent = new RegistroEventos();
		 
		 regevent.setId_tipo_cambio(idtipocambio);
		 regevent.setId_usuario(idUser);
		 regevent.setTipo(tipo);
		 registrooDao.save(regevent).subscribe();
	}

}


