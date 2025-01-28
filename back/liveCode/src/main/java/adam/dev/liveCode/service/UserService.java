package adam.dev.liveCode.service;

import adam.dev.liveCode.dao.RoomRepository;
import adam.dev.liveCode.dao.RoomUserRepository;
import adam.dev.liveCode.dao.UserRepository;
import adam.dev.liveCode.dto.UserDTO;
import adam.dev.liveCode.entity.Room;
import adam.dev.liveCode.entity.RoomUser;
import adam.dev.liveCode.entity.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private RoomUserRepository roomUserRepository;

    private RoomRepository roomRepository;

    private PasswordEncoder passwordEncoder;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void createUser(UserDTO toRegister) {
        try {
            User user = new User();
            user.setUsername(toRegister.getUsername());
            user.setEmail(toRegister.getEmail());
            user.setPassword(passwordEncoder.encode(toRegister.getPassword()));
             userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityExistsException("User already exists");
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
    }

    @PreAuthorize("hasPermission(#roomId, 'Room', '')")
    public List<User> getUsersByRoomId(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new EntityNotFoundException(String.format("Room '%s' not found", roomId)));
        List<RoomUser> roomUsers = roomUserRepository.findAllByRoom(room);

        return roomUsers.stream()
                .map(RoomUser::getUser)
                .collect(Collectors.toList());
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", id)));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
