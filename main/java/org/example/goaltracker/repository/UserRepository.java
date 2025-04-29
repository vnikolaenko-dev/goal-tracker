package org.example.goaltracker.repository;

import org.example.goaltracker.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUsersByLogin(String login);
}
