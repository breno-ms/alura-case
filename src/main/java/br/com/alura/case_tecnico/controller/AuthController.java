package br.com.alura.case_tecnico.controller;

import br.com.alura.case_tecnico.dto.LoginDTO;
import br.com.alura.case_tecnico.dto.RegisterUserDTO;
import br.com.alura.case_tecnico.entity.Role;
import br.com.alura.case_tecnico.entity.User;
import br.com.alura.case_tecnico.exception.RoleNotFoundException;
import br.com.alura.case_tecnico.exception.UsernameAlreadyTakenException;
import br.com.alura.case_tecnico.exception.UserNotFoundException;
import br.com.alura.case_tecnico.repository.RoleRepository;
import br.com.alura.case_tecnico.repository.UserRepository;
import br.com.alura.case_tecnico.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO body) {
        User user = this.userRepository
                .findByEmail(body.email())
                .orElseThrow(UserNotFoundException::new);

        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterUserDTO body) {
        Optional<User> user = this.userRepository.findByUsername(body.username());

        if (user.isPresent()) {
            throw new UsernameAlreadyTakenException();
        }

        String roleWithPrefix = "ROLE_" + body.role();

        Role role = roleRepository
                .findByRoleName(roleWithPrefix)
                .orElseThrow(RoleNotFoundException::new);

        User newUser = new User(body, passwordEncoder, role);
        userRepository.save(newUser);

        String token = this.tokenService.generateToken(newUser);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
