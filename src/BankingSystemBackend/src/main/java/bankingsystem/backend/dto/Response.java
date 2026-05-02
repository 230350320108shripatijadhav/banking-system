package bankingsystem.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private String status;
    private String message;

    // Optional: static helper methods (recommended)
    public static Response success(String message) {
        return new Response("SUCCESS", message);
    }

    public static Response error(String message) {
        return new Response("ERROR", message);
    }
}