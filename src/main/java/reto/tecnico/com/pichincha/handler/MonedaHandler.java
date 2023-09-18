package reto.tecnico.com.pichincha.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reto.tecnico.com.pichincha.entity.Moneda;
import reto.tecnico.com.pichincha.entity.TipoCambio;
import reto.tecnico.com.pichincha.entity.Transaccion;
import reto.tecnico.com.pichincha.service.IMonedaService;

@Component
@Slf4j
@RequiredArgsConstructor
public class MonedaHandler {
	@Autowired
    private IMonedaService monedaService;
    
    public MonedaHandler(IMonedaService monedaService) {
        this.monedaService = monedaService;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        Flux<TipoCambio> moneda = monedaService.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(moneda, TipoCambio.class);
    }

    public Mono<ServerResponse> change(ServerRequest request) {
    	int id = Integer.valueOf(request.pathVariable("id"));
    	Mono<Transaccion> dtoMono = request.bodyToMono(Transaccion.class);
    	return dtoMono.flatMap(monedaDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(monedaService.change(monedaDto,id), Transaccion.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
    	int id = Integer.valueOf(request.pathVariable("id"));
    	Mono<TipoCambio> dtoMono = request.bodyToMono(TipoCambio.class);
    	return dtoMono.flatMap(monedaDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(monedaService.update(monedaDto,id), TipoCambio.class));
    }
    
    public Mono<ServerResponse> save(ServerRequest request) {
    	int id = Integer.valueOf(request.pathVariable("id"));
    	Mono<TipoCambio> dtoMono = request.bodyToMono(TipoCambio.class);
    	return dtoMono.flatMap(monedaDto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(monedaService.save(monedaDto,id), TipoCambio.class));
    }

}
