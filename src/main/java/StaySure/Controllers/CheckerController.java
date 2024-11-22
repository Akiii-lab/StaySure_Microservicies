package StaySure.Controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import StaySure.Repositories.DTO.CheckerDTO;
import StaySure.Services.CheckerService;

@RestController
@RequestMapping("/api/checker")
public class CheckerController {

    @Autowired
    CheckerService service;

    @GetMapping("/itself")
    public ResponseEntity<HashMap<String, Object>> getitself() {
        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Object> response = new HashMap<>();
        CheckerDTO user = service.findCheckerByEmail(auth.getName());
        if (user == null) {
            response.put("error", "User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.put("data", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> getUserByID(@PathVariable("id") Long id) {
        HashMap<String, Object> response = new HashMap<>();
        CheckerDTO user = service.findCheckerById(id);
        if (user == null) {
            response.put("error", "User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.put("data", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
