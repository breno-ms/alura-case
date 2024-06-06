package br.com.alura.case_tecnico.service;

import br.com.alura.case_tecnico.dto.UserResponseDTO;
import br.com.alura.case_tecnico.entity.user.User;
import br.com.alura.case_tecnico.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> findAll() {
        List<User> users = this.userRepository.findAll();
        List<UserResponseDTO> usersOutDto = new ArrayList<>();
        for (User user : users) {
            usersOutDto.add(new UserResponseDTO(user.getUsername(), user.getEmail(), user.getRole().getRoleName()));
        }
        return usersOutDto;
    }

    public UserResponseDTO findByUsername(String username) throws Exception {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found"));
        return new UserResponseDTO(user.getUsername(), user.getEmail(), user.getRole().getRoleName());
    }

}
