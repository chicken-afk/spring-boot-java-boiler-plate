package roomchat.backend_api.services;

import java.util.HashMap;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import roomchat.backend_api.entities.User;
import roomchat.backend_api.exceptions.ErrorException;
import roomchat.backend_api.models.LoginResponse;
import roomchat.backend_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;
import roomchat.backend_api.utils.BCrypt;
import roomchat.backend_api.models.TokenData;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public LoginResponse login(String email, String password) {
        // Check if user exists
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            var errors = new HashMap<String, List<String>>();
            errors.put("email", List.of("Email not found"));
            return new ErrorException("Login failed", HttpStatus.UNPROCESSABLE_ENTITY, errors);
        });

        // Check if password is correct
        if (!BCrypt.checkpw(password, user.getPassword())) {
            var errors = new HashMap<String, List<String>>();
            errors.put("password", List.of("Incorrect password"));
            throw new ErrorException("Login failed", HttpStatus.UNPROCESSABLE_ENTITY,
                    errors);
        }
        // var token
        var tokenData = TokenData.builder()
                .accessToken("sample_token_1234567890")
                .refreshToken("sample_refresh_token_1234567890")
                .accessUuid("sample_access_uuid_1234567890")
                .refreshUuid("sample_refresh_uuid_1234567890")
                .atExpires(3600L) // Token expiration time in seconds
                .rtExpires(null) // Set to null for now
                .build();
        var loginRes = LoginResponse.builder()
                .email(email)
                .uuid(user.getUuid())
                .token(tokenData)
                .build();

        return loginRes;
    }

}
