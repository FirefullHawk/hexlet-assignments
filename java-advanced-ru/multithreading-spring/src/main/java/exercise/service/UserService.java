package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> findById(int id) {
        return userRepository.findById(id);
    }


    public Mono<User> update(int userId, User user) {
        user.setId(userId);
        return userRepository.save(user);
    }

    public Mono<Void> deleteById(int userId) {
        return userRepository.deleteById(userId);
    }
    // END
}
