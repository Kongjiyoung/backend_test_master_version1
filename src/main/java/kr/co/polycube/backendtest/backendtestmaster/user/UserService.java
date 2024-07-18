package kr.co.polycube.backendtest.backendtestmaster.user;

import jakarta.transaction.Transactional;
import kr.co.polycube.backendtest.backendtestmaster._core.errors.exception.Exception400;
import kr.co.polycube.backendtest.backendtestmaster._core.errors.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserResponse.SaveUserDTO saveUser(UserRequest.SaveUserDTO requestDTO) {

        User user=userRepository.save(requestDTO.toEntity());

        return new UserResponse.SaveUserDTO(user);
    }

    public UserResponse.findUserDTO findUser(Long id) {

        User user=userRepository.findById(id).orElseThrow(() -> new Exception404("존재하지 않는 유저 번호입니다"));

        return new UserResponse.findUserDTO(user);
    }

    @Transactional
    public UserResponse.UpdateUserDTO updateUser(Long userId, UserRequest.UpdateDTO requestDTO) {

        User user=userRepository.findById(userId).orElseThrow(() -> new Exception404("존재하지 않는 유저 번호입니다"));

        user.setName(requestDTO.getName());

        return new UserResponse.UpdateUserDTO(user);
    }
}
