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
    public ResponseEntity<?> userSave(@RequestBody UserRequest.UserSaveDTO requestDTO){

        UserResponse.UserSaveDTO responseDTO=userService.saveUser(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }
}
