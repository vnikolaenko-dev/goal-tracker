package org.example.goaltracker.service;

import lombok.Data;
import org.example.goaltracker.model.entity.User;
import org.example.goaltracker.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
@Data
public class UserService {
    private final UserRepository userRepository;

    public User findUserByLogin(String login) {
        return userRepository.getUsersByLogin(login);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}
