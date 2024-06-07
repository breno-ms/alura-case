package br.com.alura.case_tecnico.controller;

import br.com.alura.case_tecnico.dto.LoginDTO;
import br.com.alura.case_tecnico.dto.RegisterUserDTO;
import br.com.alura.case_tecnico.entity.role.Role;
import br.com.alura.case_tecnico.entity.user.User;
import br.com.alura.case_tecnico.repository.UserRepository;
import br.com.alura.case_tecnico.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final TokenService tokenService;

    private static final Map<String, Integer> ROLE_IDS = new HashMap<>();

    static {
        ROLE_IDS.put("ADMIN", 1);
        ROLE_IDS.put("INSTRUCTOR", 2);
        ROLE_IDS.put("STUDENT", 3);
    }

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO body) {
        // TODO: criar exception personalizada
        User user = this.userRepository
                .findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterUserDTO body) {
        Optional<User> user = this.userRepository.findByUsername(body.username());

        if (user.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        Integer roleId = ROLE_IDS.get(body.role());

        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(body.password()));
        newUser.setEmail(body.email());
        newUser.setUsername(body.username());
        newUser.setRole(new Role(roleId, body.role()));
        newUser.setCreatedAt(LocalDate.now());
        this.userRepository.save(newUser);

        String token = this.tokenService.generateToken(newUser);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
