package br.com.alura.case_tecnico.controller;

import br.com.alura.case_tecnico.dto.UserResponseDTO;
import br.com.alura.case_tecnico.dto.UsernameRequestDTO;
import br.com.alura.case_tecnico.exception.UserNotFoundException;
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

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find-by-username/{username}")
    public ResponseEntity<UserResponseDTO> findByUsername(@PathVariable String username) throws UserNotFoundException {
        UserResponseDTO user = userService.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
