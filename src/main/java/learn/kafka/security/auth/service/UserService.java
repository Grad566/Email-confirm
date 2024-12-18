package learn.kafka.security.auth.service;

import learn.kafka.security.auth.component.JwtTokenProvider;
import learn.kafka.security.auth.dto.UserDto;
import learn.kafka.security.auth.model.Code;
import learn.kafka.security.auth.model.User;
import learn.kafka.security.auth.repository.CodeRepository;
import learn.kafka.security.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private KafkaMessageProducer messageProducer;

    public User registerNewUserAccount(UserDto userDto) {
        Map<String, String> message = new HashMap<>();

        if (emailExists(userDto.getEmail())) {
            throw new RuntimeException("There is an account with that email address: "
                    + userDto.getEmail());
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        repository.save(user);

        Code code = new Code();
        code.setUser(user);

        codeRepository.save(code);

        message.put("email", user.getEmail());
        message.put("code", code.getId().toString());
        messageProducer.sendMessage("code", message);

        return user;
    }

    private boolean emailExists(String email) {
        return repository.findByEmail(email) != null;
    }

    public String generateToken(Long code) {
        Code codeFromDb = codeRepository.findById(code).get();
        User user = codeFromDb.getUser();
        return jwtTokenProvider.generateToken(user);
    }
}
