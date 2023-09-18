package reto.tecnico.com.pichincha.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reto.tecnico.com.pichincha.exception.CustomException;
import reto.tecnico.com.pichincha.security.dto.CreateUserDto;
import reto.tecnico.com.pichincha.security.dto.LoginDto;
import reto.tecnico.com.pichincha.security.dto.TokenDto;
import reto.tecnico.com.pichincha.security.entity.User;
import reto.tecnico.com.pichincha.security.enums.Role;
import reto.tecnico.com.pichincha.security.jwt.JwtProvider;
import reto.tecnico.com.pichincha.security.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private JwtProvider jwtProvider;
	@Autowired
    private PasswordEncoder passwordEncoder;

    public Mono<TokenDto> login(LoginDto dto) {
        return userRepository.findByUsername(dto.getUsername())
                .filter(user -> passwordEncoder.matches(dto.getPassword(), user.getPassword()))
                .map(user -> new TokenDto(jwtProvider.generateToken(user)))
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "bad credentials")));
    }

    public Mono<User> create(CreateUserDto dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Role.ROLE_USER.name())
                .build();
        Mono<Boolean> userExists = userRepository.findByUsername(user.getUsername()).hasElement();
        return userExists
                .flatMap(exists -> exists ?
                        Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "username or email already in use"))
                        : userRepository.save(user));
    }
}
