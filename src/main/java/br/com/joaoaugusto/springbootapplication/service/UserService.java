package br.com.joaoaugusto.springbootapplication.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.joaoaugusto.springbootapplication.dto.DeposityDto;
import br.com.joaoaugusto.springbootapplication.dto.UserDto;
import br.com.joaoaugusto.springbootapplication.exception.AppException;
import br.com.joaoaugusto.springbootapplication.model.User;
import br.com.joaoaugusto.springbootapplication.repository.UserRepository;

@Service
public class UserService {

    // Linkando o repositório

    private final UserRepository userRepository;

    // Constructor

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Método de checagem de email e cpf -> evitando repetição de código em create e update

    private void checkEmailAndCpf(final UserDto data) {
        if(userRepository.existsUserByCpf(data.getCpf())) {
            throw new AppException("Cpf already in use", HttpStatus.CONFLICT);
        }
        if(userRepository.existsUserByEmail(data.getEmail())) {
            throw new AppException("Email already in use", HttpStatus.CONFLICT);
        }
    }

    // CRUD

    public User createUser(final UserDto data) {
        checkEmailAndCpf(data);
        final User user = new User(data.getName(), data.getCpf(), data.getEmail(), data.getPassword(), data.getType());
        return userRepository.save(user);
    }

    public List<User> readUsers(){
        return userRepository.findAll();
    }

    public User reatrieveUser(final long id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
    }

    public User updateUser(final UserDto data, final long id) {
        checkEmailAndCpf(data);
        final User user = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        user.setName(data.getName());
        user.setCpf(data.getCpf());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        user.setType(data.getType());
        return userRepository.save(user);
    }

    public void deleteUser(final long id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }

    public User createDeposity(final DeposityDto data, final long id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        final float currentBalance = user.getBalance();
        user.setBalance(currentBalance + data.getValue());
        return userRepository.save(user);
    }

}
