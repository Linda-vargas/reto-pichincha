package reto.tecnico.com.pichincha.router;

import lombok.extern.slf4j.Slf4j;
import reto.tecnico.com.pichincha.handler.MonedaHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Slf4j
public class MonedaRouter {

    private static final String PATH = "api";



    @Bean
    RouterFunction<ServerResponse> router(MonedaHandler handler) {
        return RouterFunctions.route()
                .GET(PATH, handler::findAll)
                .POST(PATH + "/save/{id}", handler::save)
                .POST(PATH + "/{id}", handler::change)
                .PUT(PATH + "/{id}", handler::update)
//                .DELETE(PATH + "/{id}", handler::delete)
                .build();
    }
}
