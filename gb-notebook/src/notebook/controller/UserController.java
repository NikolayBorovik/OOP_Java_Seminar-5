package notebook.controller;

import notebook.model.User;
import notebook.repository.GBRepository;

import java.util.List;
import java.util.Objects;

public class UserController {
    private final GBRepository<User, Long> repository;

    public UserController(GBRepository<User, Long> repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.create(user);
    }

    public User readUser(Long userId) throws Exception {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }

        throw new RuntimeException("User not found");
    }

    public List <User> readAllUsers() {
        return repository.findAll();
    }

    public void updateUser(Long id, User user) throws Exception {
        user.setId(id);
        repository.update(id, user);

//        throw new RuntimeException("User not found");
    }

    public void deleteUser(Long usId) {
        repository.delete(usId);
    }
}
