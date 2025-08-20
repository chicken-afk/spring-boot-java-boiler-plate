package roomchat.backend_api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse<T> {
    private String message;

    @Builder.Default
    private Boolean success = false;

    private T errors;
}
