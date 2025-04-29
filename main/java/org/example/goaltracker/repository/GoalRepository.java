package org.example.goaltracker.repository;

import org.example.goaltracker.model.entity.Goal;
import org.example.goaltracker.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> getAllByUser(User user);
}
