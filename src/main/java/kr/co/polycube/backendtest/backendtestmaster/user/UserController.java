package kr.co.polycube.backendtest.backendtestmaster.user;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@Validated @RequestBody UserRequest.SaveUserDTO requestDTO, Errors errors){

        UserResponse.SaveUserDTO responseDTO=userService.saveUser(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUser(@PathVariable Long id){

        UserResponse.findUserDTO responseDTO = userService.findUser(id);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid  @RequestBody UserRequest.UpdateDTO requestDTO, Errors errors){

        UserResponse.UpdateUserDTO responseDTO = userService.updateUser(id, requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/users/{id}?name=test!!")
    public ResponseEntity<?> filterTest(@PathVariable Long id){

        return ResponseEntity.ok(null);
    }

}
