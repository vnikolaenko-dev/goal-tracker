package org.example.goaltracker.controller;

import lombok.RequiredArgsConstructor;
import org.example.goaltracker.model.entity.User;
import org.example.goaltracker.model.response.JwtResponse;
import org.example.goaltracker.security.DatabaseCrypto;
import org.example.goaltracker.service.UserService;
import org.example.goaltracker.util.JwtTokenUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final DatabaseCrypto crypto;

    /**
     * Обрабатывает запрос на вход пользователя.
     *
     * @param user Объект пользователя с email и паролем
     * @return JwtResponse с токеном или сообщением об ошибке
     */
    @PostMapping("/login")
    public JwtResponse login(@RequestBody User user) {
        User userFromDB = userService.findUserByLogin(user.getLogin());

        // Проверяем существование пользователя и совпадение паролей
        if (userFromDB != null && crypto.decrypt(userFromDB.getPassword()).equals(String.valueOf(user.getPassword()))) {
            return new JwtResponse("OK",
                    jwtTokenUtils.generateToken(user.getLogin()));
        } else {
            return new JwtResponse("ERROR", null);
        }
    }

    /**
     * Обрабатывает запрос на регистрацию нового пользователя.
     *
     * @param user Данные нового пользователя
     * @return JwtResponse с токеном или сообщением об ошибке
     */
    @PostMapping("/register")
    public JwtResponse register(@RequestBody User user) {
        // Проверка на существующего пользователя
        if (userService.findUserByLogin(user.getLogin()) != null) {
            return new JwtResponse("ERROR", null);
        }

        // Шифрование пароля и сохранение пользователя
        user.setPassword(crypto.encrypt(user.getPassword()));
        userService.addUser(user);

        // Генерация токена для нового пользователя
        String token = jwtTokenUtils.generateToken(user.getLogin());
        return new JwtResponse("OK", token);
    }
}
