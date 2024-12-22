package adam.dev.liveCode.service;

import adam.dev.liveCode.entity.User;
import adam.dev.liveCode.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);


    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
