package dfsp.security;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static dfsp.commons.Naming.SIGN_UP_URL;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class UserController {


    private CustomUserService customUserService;

    public UserController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("users")
    public List<UserApp> getUsers() {
        return customUserService.findAll();
    }

    @PostMapping(SIGN_UP_URL)
    public ResponseEntity<String> signUp(@RequestBody LoginUser loginUser) {
        UserDetails userDetails = customUserService.loadUserByUsername(loginUser.getUsername());

        if (userDetails == null) {
            customUserService.save(loginUser);
            return new ResponseEntity<>(loginUser.getUsername(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Username: " + loginUser.getUsername() + " already exist!", HttpStatus.CONFLICT);
        }
    }
}
