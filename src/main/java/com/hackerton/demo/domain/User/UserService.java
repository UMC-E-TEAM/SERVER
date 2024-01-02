package com.hackerton.demo.domain.User;

import com.hackerton.demo.domain.User.request.LoginDto;
import com.hackerton.demo.domain.common.ApiResponse;
import com.hackerton.demo.domain.common.ApiResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserDto login(LoginDto request) {

        User user = new User();
        user.setNickName(request.getNickName());

        userRepository.save(user);

        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .nickName(user.getNickName())
                .build();

        return userDto;
    }

}
