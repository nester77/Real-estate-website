package com.nester.Rew.service.mapper;

import com.nester.Rew.data.entity.User;
import com.nester.Rew.service.dto.user.UserDto;
import com.nester.Rew.service.dto.user.UserDtoForSave;
import com.nester.Rew.service.dto.user.UserDtoForUpdate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoForSavingToUser(UserDtoForSave userDtoForSaving);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    User userDtoForUpdatingToUser(UserDtoForUpdate userDtoForUpdating);
}
