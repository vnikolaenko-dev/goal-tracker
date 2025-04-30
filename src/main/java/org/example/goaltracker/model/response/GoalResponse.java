package org.example.goaltracker.model.response;

import lombok.Data;
import org.example.goaltracker.model.entity.Goal;

import java.util.ArrayList;

@Data
public class GoalResponse {
    private ArrayList<Goal> goals;

    public GoalResponse(ArrayList<Goal> goals) {
        this.goals = goals;
    }
}
