package StaySure.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/authorized")
public class AuthorizedController {
    @GetMapping("")
    public ResponseEntity<HashMap<String, Object>> getMethodName() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "You are authorized!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
