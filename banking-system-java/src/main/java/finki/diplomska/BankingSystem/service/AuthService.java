package finki.diplomska.BankingSystem.service;

import finki.diplomska.BankingSystem.model.User;
import finki.diplomska.BankingSystem.model.enums.Role;
import finki.diplomska.BankingSystem.model.enums.UserStatus;
import finki.diplomska.BankingSystem.repository.UserRepository;
import finki.diplomska.BankingSystem.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRole(Role.CUSTOMER);
        user.setStatus(UserStatus.ACTIVE);

        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String login(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        User user = userRepository.findByEmail(email).orElseThrow();
        return jwtService.generateToken(user);
    }
}