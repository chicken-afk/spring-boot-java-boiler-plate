package roomchat.backend_api.exceptions;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ErrorException extends RuntimeException {
    private final HttpStatus status;
    private final Map<String, List<String>> errorDatas;

    public ErrorException(String message, HttpStatus status, Map<String, List<String>> errorDatas) {
        super(message);
        this.status = status;
        this.errorDatas = errorDatas;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, List<String>> getErrors() {
        return errorDatas;
    }
}
