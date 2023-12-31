package com.sos.trellosos.domain.user;

import com.sos.trellosos.domain.jwt.JwtUtil;
import com.sos.trellosos.domain.security.UserDetailsImpl;
import com.sos.trellosos.global.dto.CommonResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(
            @Valid @RequestBody UserRequestDto userRequestDto,
            BindingResult bindingResult
    ) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body(new CommonResponseDto("회원가입 실패", HttpStatus.BAD_REQUEST.value()));
        }

        userService.signup(userRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto> login(
            @RequestBody UserRequestDto userRequestDto,
            HttpServletResponse response
    ) {
        userService.login(userRequestDto);

        jwtUtil.addJwtToCookie(jwtUtil.createToken(userRequestDto.getUsername()), response);

        return ResponseEntity.ok()
                .body(new CommonResponseDto("로그인 성공", HttpStatus.OK.value()));
    }

    // 회원탈퇴
    @DeleteMapping
    public ResponseEntity<CommonResponseDto> deleteUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        userService.deleteUser(userDetails);

        return ResponseEntity.ok()
                .body(new CommonResponseDto("회원탈퇴 성공", HttpStatus.OK.value()));
    }

    // 회원정보 수정
    @PatchMapping
    ResponseEntity<CommonResponseDto> updateUser(
            @Valid @RequestBody UserRequestDto userRequestDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                    .body(new CommonResponseDto("회원정보 수정 실패", HttpStatus.BAD_REQUEST.value()));
        }

        userService.updateUser(userRequestDto, userDetails);

        return ResponseEntity.ok()
                .body(new CommonResponseDto("회원정보 수정 성공", HttpStatus.OK.value()));
    }
}