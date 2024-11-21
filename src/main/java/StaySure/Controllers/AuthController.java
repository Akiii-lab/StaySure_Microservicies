package StaySure.Controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import StaySure.Services.UserService;
import StaySure.Services.AdminService;
import StaySure.Services.LesseService;
import StaySure.Services.CheckerService;
import StaySure.Security.JWTGenerator;

import StaySure.Repositories.DTO.UserDTO;
import StaySure.Repositories.DTO.AdminDTO;
import StaySure.Repositories.DTO.LesseDTO;
import StaySure.Repositories.DTO.CheckerDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    LesseService lesseService;
    @Autowired
    CheckerService checkerService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTGenerator jwtGenerator;

    @PostMapping("/user/register")
    public ResponseEntity<HashMap<String, Object>> createUser(@RequestBody UserDTO user) {
        System.out.println("Creating user");
        HashMap<String, Object> response = new HashMap<>();
        String encryptedPassword = passwordEncoder.encode(user.password);
        user.password = (encryptedPassword);
        UserDTO res = userService.createUser(user);
        if (res == null) {
            response.put("error", "Error creating customer");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("data", "Customer created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/lesse/register")
    public ResponseEntity<HashMap<String, Object>> createLisse(@RequestBody LesseDTO user) {
        System.out.println("Creating user");
        HashMap<String, Object> response = new HashMap<>();
        String encryptedPassword = passwordEncoder.encode(user.password);
        user.password = (encryptedPassword);
        LesseDTO res = lesseService.createLesse(user);
        if (res == null) {
            response.put("error", "Error creating customer");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("data", "Customer created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/checker/register")
    public ResponseEntity<HashMap<String, Object>> createChecker(@RequestBody CheckerDTO user) {
        System.out.println("Creating user");
        HashMap<String, Object> response = new HashMap<>();
        String encryptedPassword = passwordEncoder.encode(user.password);
        user.password = (encryptedPassword);
        CheckerDTO res = checkerService.createChecker(user);
        if (res == null) {
            response.put("error", "Error creating customer");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("data", "Customer created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<HashMap<String, Object>> authenticateUser(@RequestBody UserDTO user) {

        UserDTO existinguser = userService.findUserByEmail(user.email);

        if (existinguser == null
                || !passwordEncoder.matches(user.password, existinguser.password)) {
            HashMap<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                existinguser.email,
                existinguser.password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtGenerator.generateToken(authentication);

        HashMap<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/lesse/login")
    public ResponseEntity<HashMap<String, Object>> authenticateLesse(@RequestBody LesseDTO user) {

        LesseDTO existinguser = lesseService.findLesseByEmail(user.email);

        if (existinguser == null
                || !passwordEncoder.matches(user.password, existinguser.password)) {
            HashMap<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                existinguser.email,
                existinguser.password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtGenerator.generateToken(authentication);

        HashMap<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/checker/login")
    public ResponseEntity<HashMap<String, Object>> authenticateLesse(@RequestBody CheckerDTO user) {

        CheckerDTO existinguser = checkerService.findCheckerByEmail(user.email);

        if (existinguser == null
                || !passwordEncoder.matches(user.password, existinguser.password)) {
            HashMap<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                existinguser.email,
                existinguser.password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtGenerator.generateToken(authentication);

        HashMap<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<HashMap<String, Object>> authenticateAdmin(@RequestBody AdminDTO user) {

        AdminDTO existinguser = adminService.findAdminByEmail(user.email);

        if (existinguser == null
                || !passwordEncoder.matches(user.password, existinguser.password)) {
            HashMap<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                existinguser.email,
                existinguser.password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtGenerator.generateToken(authentication);

        HashMap<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        return ResponseEntity.ok(response);
    }
}