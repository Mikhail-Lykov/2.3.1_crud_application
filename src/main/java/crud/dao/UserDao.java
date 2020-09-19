package crud.dao;

import crud.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUsers();
    void add(User user);
    void delete(Long id);
    void edit(User user);
    User findUser(Long id);
}
