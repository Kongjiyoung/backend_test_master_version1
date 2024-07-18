package kr.co.polycube.backendtest.backendtestmaster.user;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest.UserSaveDTO requestDTO){

        UserResponse.SaveUserDTO responseDTO=userService.saveUser(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUser(@PathVariable Long id){

        UserResponse.findUserDTO responseDTO = userService.findUser(id);

        return ResponseEntity.ok(responseDTO);
    }
}
