package com.github.kadehar.tests;

import com.github.kadehar.model.Data;
import com.github.kadehar.model.User;
import com.github.kadehar.model.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ReqresTest extends TestBase {
    private int id;
    private User user;
    private UserData data;

    @Test
    @DisplayName("Тест с использованием магии Groovy")
    void groovyMagicTest() {
        step("Поиск пользователя в списке, используя Groovy", () -> {
            steps.checkUserWithGroovyMagic();
        });
    }

    @Test
    @DisplayName("Регистрация нового пользователя")
    void registerNewUser() {
        step("Регистрация нового пользователя", () -> {
            step("Получить id пользователя", () -> {
                id = steps.registerNewUser();
            });
            step("Проверить что id равен 4", () -> {
                assertThat(id, is(4));
            });
        });
    }

    @Test
    @DisplayName("Получение пользователя по id")
    void getUserById() {
        step("Получить пользователя по id", () -> {
            step("Получение данных пользователя", () -> {
                data = steps.getUserById(4);
            });
            step("Проверить что email пользователя равен eve.holt@reqres.in", () -> {
                assertThat(data.getData().getEmail(), is(equalTo("eve.holt@reqres.in")));
            });
        });
    }

    @Test
    @DisplayName("Пользователь успешно вошел в систему")
    void successfulLogin() {
        step("Пользователь залогинен успешно", () -> {
            steps.successfulLogin();
        });
    }

    @Test
    @DisplayName("Пользователь успешно удалён")
    void deleteUser() {
        step("Пользователь удалён успешно", () -> {
            steps.deleteUser(4);
        });
    }
}
