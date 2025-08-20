package roomchat.backend_api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenData {
    private String accessToken;
    private String refreshToken;
    private String accessUuid;
    private String refreshUuid;
    private Long atExpires;
    private Long rtExpires;
}
