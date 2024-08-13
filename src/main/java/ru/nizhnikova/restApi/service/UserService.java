package ru.nizhnikova.restApi.service;

import ru.nizhnikova.restApi.model.User;

public interface UserService {

    void getUsers();

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

}
