package com.sos.trellosos.domain.column.service;

import com.sos.trellosos.domain.column.dto.ColumnCreateRequestDto;
import com.sos.trellosos.domain.column.dto.ColumnCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ColumnService {
}
