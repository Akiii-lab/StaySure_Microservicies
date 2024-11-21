package StaySure.Controllers;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public ResponseEntity<HashMap<String, Object>> index() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "Hello World!");
        response.put("info", "Welcome to our API! to use it visit /api/v1");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/api/v1")
    public ResponseEntity<HashMap<String, Object>> index2() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to our API!");
        response.put("info",
                "To use any endpoint, visit /api/v1/{endpoint} (first need to authenticate, see /auth");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}