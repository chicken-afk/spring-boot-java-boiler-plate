package roomchat.backend_api.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import roomchat.backend_api.response.ErrorResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidationUtils {
    public static ErrorResponse<Map<String, List<String>>> getValidationErrors(BindingResult bindingResult) {
        Map<String, List<String>> errors = bindingResult.getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));
        String message = "";
        for (Map.Entry<String, List<String>> entry : errors.entrySet()) {
            message = message + entry.getValue().get(0) + ", ";
        }
        // remove the last comma
        message = message.substring(0, message.length() - 2);
        return ErrorResponse.<Map<String, List<String>>>builder()
                .message(message)
                .errors(errors)
                .build();
    }
}
