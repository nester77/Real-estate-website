package com.nester.Rew.service.impl;

import com.nester.Rew.data.entity.User;
import com.nester.Rew.data.repository.UserRepository;
import com.nester.Rew.service.UserService;
import com.nester.Rew.service.dto.user.UserDto;
import com.nester.Rew.service.dto.user.UserDtoForSave;
import com.nester.Rew.service.dto.user.UserDtoForUpdate;
import com.nester.Rew.service.exception.NotFoundException;
import com.nester.Rew.service.exception.RewException;
import com.nester.Rew.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final String USER_NOT_FOUND_MSG = "User not found";
    private static final String EMAIL_ALREADY_EXISTS_MSG = "Email %s already exists in the database";
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserDtoForSave dto) {
        Optional<User> existing = userRepository.findByEmail(dto.getEmail());
        if (existing.isPresent()) {
            throw new RewException(String.format(EMAIL_ALREADY_EXISTS_MSG, dto.getEmail()));
        }
        User entity = userMapper.userDtoForSavingToUser(dto);
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(encodedPassword);
        entity.setRole(User.Role.USER);
        User created = userRepository.save(entity);
        return userMapper.userToUserDto(created);
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
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MSG));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto update(UserDtoForUpdate dto) {
        Optional<User> existing = userRepository.findByEmail(dto.getEmail());
        if (existing.isPresent() && !existing.get().getId().equals(dto.getId())) {
            throw new RewException(String.format(EMAIL_ALREADY_EXISTS_MSG, dto.getEmail()));
        }
        User oldUser = userRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MSG));
        User newUser = userMapper.userDtoForUpdatingToUser(dto);
        User updated = userRepository.save(newUser);
        return userMapper.userToUserDto(updated);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MSG));
        userRepository.deleteById(id);
    }

    @Override
    public void registerUser(UserDtoForSave dto) {

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserAppDetails(userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MSG)));
    }
}
