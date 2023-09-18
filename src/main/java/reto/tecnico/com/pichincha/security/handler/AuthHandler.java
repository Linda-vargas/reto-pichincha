package reto.tecnico.com.pichincha.security.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reto.tecnico.com.pichincha.exception.CustomException;
import reto.tecnico.com.pichincha.security.dto.CreateUserDto;
import reto.tecnico.com.pichincha.security.dto.LoginDto;
import reto.tecnico.com.pichincha.security.dto.TokenDto;
import reto.tecnico.com.pichincha.security.entity.User;
import reto.tecnico.com.pichincha.security.service.UserService;
import reto.tecnico.com.pichincha.validation.ObjectValidator;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthHandler {
	@Autowired
    private UserService userService;

	@Autowired
    private ObjectValidator objectValidator;

    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<LoginDto> dtoMono = request.bodyToMono(LoginDto.class).doOnNext(t -> {
			try {
				objectValidator.validate(t);
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        return dtoMono
                .flatMap(dto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userService.login(dto), TokenDto.class));
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<CreateUserDto> dtoMono = request.bodyToMono(CreateUserDto.class).doOnNext(t -> {
			try {
				objectValidator.validate(t);
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        return dtoMono
                .flatMap(dto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userService.create(dto), User.class));
    }
}
