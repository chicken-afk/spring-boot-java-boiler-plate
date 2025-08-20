package roomchat.backend_api.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import roomchat.backend_api.utils.ValidationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import roomchat.backend_api.models.LoginResponse;
import roomchat.backend_api.requests.LoginRequest;
import roomchat.backend_api.services.AuthService;
import org.springframework.http.MediaType;
import roomchat.backend_api.response.SuccessResponse;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/api/v1/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse = ValidationUtils.getValidationErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        var res = authService.login(request.getEmail(), request.getPassword());
        var successData = SuccessResponse.<LoginResponse>builder()
                .message("Login successful")
                .data(res)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(successData);
    }
}