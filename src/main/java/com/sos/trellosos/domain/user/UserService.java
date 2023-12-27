package com.sos.trellosos.domain.user;

import com.sos.trellosos.exception.CustomException;
import com.sos.trellosos.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();
        String checkPassword = userRequestDto.getCheckPassword();
        String email = userRequestDto.getEmail();

        String encodedPassword = passwordEncoder.encode(password);
        String encodedCheckPassword = passwordEncoder.encode(checkPassword);

        if (userRepository.findByUsername(username).isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_EXIST_USER);
        }

        if (!passwordEncoder.matches(password, encodedCheckPassword)) {
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCHES);
        }

        if (password.contains(username)) {
            throw new CustomException(ErrorCode.ID_PW_SAME);
        }

        User user = new User(username, encodedPassword, email);
        userRepository.save(user);
    }

    public void login(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_MATCHES2));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCHES);
        }
    }
}
