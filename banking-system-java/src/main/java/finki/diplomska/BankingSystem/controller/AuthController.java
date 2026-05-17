package finki.diplomska.BankingSystem.controller;

import finki.diplomska.BankingSystem.model.User;
import finki.diplomska.BankingSystem.model.dto.LoginRequest;
import finki.diplomska.BankingSystem.model.dto.RegisterRequest;
import finki.diplomska.BankingSystem.service.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<@NonNull String> register(@RequestBody RegisterRequest request) {
        String token = authService.register(
                request.firstName(), request.lastName(), request.email(), request.password()
        );
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<@NonNull String> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.email(), request.password());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/me")
    public ResponseEntity<@NonNull User> getCurrentUser(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(currentUser);
    }
}