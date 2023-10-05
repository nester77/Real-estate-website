package com.nester.Rew.service.impl;

import com.nester.Rew.data.entity.User;
import com.nester.Rew.data.repository.UserRepository;
import com.nester.Rew.service.UserService;
import com.nester.Rew.service.dto.user.UserDto;
import com.nester.Rew.service.dto.user.UserDtoForSave;
import com.nester.Rew.service.dto.user.UserDtoForUpdate;
import com.nester.Rew.service.exception.NotFoundException;
import com.nester.Rew.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String USER_NOT_FOUND_MSG = "User not found";
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto create(UserDtoForSave dto) {
        return null;
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::userToUserDto);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MSG));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto getByEmail(String email) {
        return null;
    }

    @Override
    public UserDto update(UserDtoForUpdate dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void registerUser(UserDtoForSave dto) {

    }
}
