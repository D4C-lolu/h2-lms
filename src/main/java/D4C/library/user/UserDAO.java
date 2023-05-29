package D4C.library.user;

import D4C.library.user.User;

import java.util.List;

public interface UserDAO {
    public int insert(User u);
    public void update(Long id);
    public User getUser(Long id);
    public boolean checkStatus(Long id);
}
