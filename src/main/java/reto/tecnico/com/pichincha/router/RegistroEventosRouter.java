package reto.tecnico.com.pichincha.router;

import lombok.extern.slf4j.Slf4j;
import reto.tecnico.com.pichincha.handler.RegistroEventosHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Slf4j
public class RegistroEventosRouter {

    private static final String PATH = "eventos";



    @Bean
    RouterFunction<ServerResponse> registroRouter(RegistroEventosHandler handler) {
        return RouterFunctions.route()
                .GET(PATH+"/registro", handler::findAll)
                .build();
    }
}
