package br.com.alura.case_tecnico.service;

import br.com.alura.case_tecnico.dto.UserResponseDTO;
import br.com.alura.case_tecnico.entity.User;
import br.com.alura.case_tecnico.exception.UserNotFoundException;
import br.com.alura.case_tecnico.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> findAll() {
        List<User> users = this.userRepository.findAll();
        return users.stream()
                .map(User::convertToDto)
                .collect(Collectors.toList());
    }

    public UserResponseDTO findByUsername(String username) throws UserNotFoundException {
        User user = this.userRepository
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        return new UserResponseDTO(user);
    }

    public User findByEmailAndUsername(String email, String username) throws UserNotFoundException {
        return this.userRepository
                .findByEmailAndUsername(email, username)
                .orElseThrow(UserNotFoundException::new);
    }

}
