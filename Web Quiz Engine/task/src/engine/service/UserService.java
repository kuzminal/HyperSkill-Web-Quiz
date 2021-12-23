package engine.service;

import engine.model.entity.User;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        String pass = user.getPassword();
        user.setPassword(passwordEncoder.encode(pass));
        return userRepository.save(user);
    }

    public boolean userExists(User user) {
        return userRepository.findByUsername(user.getUsername()) != null;
    }
}
