package roomchat.backend_api.response;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse<T> {
    private String message;

    private T data;

    @Builder.Default
    private Boolean success = true;
}
