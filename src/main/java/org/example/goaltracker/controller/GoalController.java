package org.example.goaltracker.controller;

import lombok.RequiredArgsConstructor;
import org.example.goaltracker.model.Color;
import org.example.goaltracker.model.entity.Goal;
import org.example.goaltracker.model.entity.User;
import org.example.goaltracker.model.response.GoalResponse;
import org.example.goaltracker.model.response.StatusResponse;
import org.example.goaltracker.security.JwtRequestFilter;
import org.example.goaltracker.service.GoalService;
import org.example.goaltracker.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("goal")
@RequiredArgsConstructor
public class GoalController {
    private final GoalService goalService;
    private final UserService userService;
    private final JwtRequestFilter jwtRequestFilter;

    @PostMapping("create")
    public StatusResponse createGoal(@RequestHeader("Authorization") String authHeader, @RequestBody Goal goal) {
        User user = userService.findUserByLogin(jwtRequestFilter.getEmail(authHeader));
        goalService.createGoal(user, goal);
        return new StatusResponse("OK");
    }

    @GetMapping("success/{id}")
    public StatusResponse successGoal(@RequestHeader("Authorization") String authHeader, @PathVariable long id) {
        User user = userService.findUserByLogin(jwtRequestFilter.getEmail(authHeader));
        Goal goal = goalService.getGoalById(id);
        if (goal.getUser().getId() != user.getId()) {
            return new StatusResponse("FAIL");
        }
        goal.setSuccess(Boolean.TRUE);
        goalService.updateGoal(goal);
        return new StatusResponse("OK");
    }

    @GetMapping("unsuccessful/{id}")
    public StatusResponse unsuccessfulGoal(@RequestHeader("Authorization") String authHeader, @PathVariable long id) {
        User user = userService.findUserByLogin(jwtRequestFilter.getEmail(authHeader));
        Goal goal = goalService.getGoalById(id);
        if (goal.getUser().getId() != user.getId()) {
            return new StatusResponse("FAIL");
        }
        goal.setSuccess(Boolean.FALSE);
        goalService.updateGoal(goal);
        return new StatusResponse("OK");
    }

    @GetMapping("set-color/{id}/{color}")
    public StatusResponse setColorGoal(@RequestHeader("Authorization") String authHeader, @PathVariable long id, @PathVariable String color) {
        User user = userService.findUserByLogin(jwtRequestFilter.getEmail(authHeader));
        Goal goal = goalService.getGoalById(id);
        if (goal.getUser().getId() != user.getId()) {
            return new StatusResponse("FAIL");
        }
        goal.setColor(Color.valueOf(color));
        goalService.updateGoal(goal);
        return new StatusResponse("OK");
    }

    @GetMapping("get-all")
    public GoalResponse getAllGoals(@RequestHeader("Authorization") String authHeader) {
        User user = userService.findUserByLogin(jwtRequestFilter.getEmail(authHeader));
        return new GoalResponse((ArrayList<Goal>) goalService.getAllUserGoals(user));
    }

}
