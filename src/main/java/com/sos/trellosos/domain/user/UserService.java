package com.sos.trellosos.domain.user;

import com.sos.trellosos.domain.security.UserDetailsImpl;
import com.sos.trellosos.global.exception.CustomException;
import com.sos.trellosos.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
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

    // 로그인
    public void login(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_MATCHES2));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCHES);
        }
    }

    // 회원탈퇴
    public void deleteUser(UserDetailsImpl userDetails) {
        User user = userRepository.findById(userDetails.getUser().getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        userRepository.delete(user);
    }

    // 회원정보 수정
    @Transactional
    public void updateUser(UserRequestDto userRequestDto, UserDetailsImpl userDetails) {
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                () -> new IllegalArgumentException("로그인을 해주세요")
        );

        String checkPassword = userRequestDto.getCheckPassword();
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());

        if (userRepository.findByUsername(userRequestDto.getUsername()).isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_EXIST_USER);
        }

        if (!passwordEncoder.matches(userRequestDto.getPassword(), encodedPassword)) {
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCHES);
        }

        if (user.getPassword().contains(userRequestDto.getUsername())) {
            throw new CustomException(ErrorCode.ID_PW_SAME);
        }

        user.update(userRequestDto, encodedPassword);
        userRepository.save(user);
    }
}
