package kr.co.polycube.backendtest.backendtestmaster.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserResponse.UserSaveDTO saveUser(UserRequest.UserSaveDTO requestDTO) {
        User user=userRepository.save(requestDTO.toEntity());

        return new UserResponse.UserSaveDTO(user);
    }
}
