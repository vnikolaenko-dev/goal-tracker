package org.example.goaltracker.service;

import lombok.Data;
import org.example.goaltracker.model.entity.Goal;
import org.example.goaltracker.model.entity.User;
import org.example.goaltracker.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
public class GoalService {
    private final GoalRepository goalRepository;

    @Transactional
    public Goal createGoal(User user, Goal goal) {
        goal.setUser(user);
        return goalRepository.save(goal);
    }

    public Goal getGoalById(long id) {
        return goalRepository.findById(id).get();
    }

    public void updateGoal(Goal goal) {
        goalRepository.save(goal);
    }

    public List<Goal> getAllUserGoals(User user) {
        return goalRepository.getAllByUser(user);
    }
}
