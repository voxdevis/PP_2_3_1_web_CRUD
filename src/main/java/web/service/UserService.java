package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> showAll();

    User show(int id);

    void save(User user);

    void update(int id, User updatedUser);

    void delete(int id);
}
