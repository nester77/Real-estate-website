package com.nester.Rew.service;

import com.nester.Rew.service.dto.user.UserDto;
import com.nester.Rew.service.dto.user.UserDtoForSave;
import com.nester.Rew.service.dto.user.UserDtoForUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto create(UserDtoForSave dto);

    Page<UserDto> getAll(Pageable pageable);

    UserDto getById(Long id);

    UserDto getByEmail(String email);

    UserDto update(UserDtoForUpdate dto);

    void delete(Long id);

    void registerUser(UserDtoForSave dto);
}
