package zrk.learning.dao;

import zrk.learning.domain.User;

public interface UserDao {
    public User findByUsername(String username);

    void save(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);
}
