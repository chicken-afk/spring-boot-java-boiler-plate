package roomchat.backend_api.models;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Integer uuid;
    private String email;
    @Builder.Default
    private String status = "Active";
    private TokenData token;
}
