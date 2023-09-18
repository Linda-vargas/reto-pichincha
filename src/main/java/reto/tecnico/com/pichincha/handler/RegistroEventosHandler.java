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
import reto.tecnico.com.pichincha.entity.RegistroEventos;
import reto.tecnico.com.pichincha.entity.TipoCambio;
import reto.tecnico.com.pichincha.entity.Transaccion;
import reto.tecnico.com.pichincha.service.IMonedaService;
import reto.tecnico.com.pichincha.service.IRegistroEventosService;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegistroEventosHandler {
	
	@Autowired
    private IRegistroEventosService registroService;
    
    public RegistroEventosHandler(IRegistroEventosService registroService) {
        this.registroService = registroService;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        Flux<RegistroEventos> registro = registroService.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(registro, RegistroEventos.class);
    }

}
