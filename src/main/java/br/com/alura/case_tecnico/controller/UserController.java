package br.com.alura.case_tecnico.controller;

import br.com.alura.case_tecnico.dto.UserResponseDTO;
import br.com.alura.case_tecnico.dto.UsernameRequestDTO;
import br.com.alura.case_tecnico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/findByUsername")
    public ResponseEntity<?> findByUsername(@RequestBody UsernameRequestDTO username) throws Exception {
        UserResponseDTO user = userService.findByUsername(username.username());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
