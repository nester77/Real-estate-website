package com.nester.Rew.service.impl;

import com.nester.Rew.data.entity.User;
import com.nester.Rew.data.repository.UserRepository;
import com.nester.Rew.service.UserService;
import com.nester.Rew.service.dto.user.RoleDto;
import com.nester.Rew.service.dto.user.UserDto;
import com.nester.Rew.service.exception.NotFoundException;
import com.nester.Rew.service.mapper.UserMapperImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @Mock
    private static UserRepository userRepositoryMock;
    private static UserService userService;
    public static final long ID_EXISTING = 1L;
    public static final long ID_NOT_EXISTING = 999L;
    public static final String USER_EMAIL = "test@test.com";
    public static final String USER_PASSWORD = "TestPassword";
    public static final String USER_FIRST_NAME = "TestFirstName";
    public static final String USER_LAST_NAME = "TestLastName";
    public static final User.Role USER_ROLE = User.Role.USER;
    public static final RoleDto USER_DTO_ROLE = RoleDto.USER;
    private static User existing;
    private static UserDto existingDto;

    @BeforeAll
    static void beforeAll() {
        userRepositoryMock = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepositoryMock, new UserMapperImpl(), null);
    }

    @BeforeEach
    void setUp() {
        reset(userRepositoryMock);
        existing = initUser(ID_EXISTING, USER_EMAIL, USER_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_ROLE);
        existingDto = initUserDto(ID_EXISTING, USER_EMAIL, USER_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_DTO_ROLE);
    }

    @Test
    void getByIdExistingTest() {
        when(userRepositoryMock.findById(ID_EXISTING)).thenReturn(Optional.of(existing));
        UserDto fromService = userService.getById(ID_EXISTING);
        assertEquals(existingDto, fromService);
    }

    @Test
    void getByIdNotExistingTest() {
        when(userRepositoryMock.findById(ID_EXISTING)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getById(ID_NOT_EXISTING));
    }

    @Test
    void getByEmailExistingTest() {
        when(userRepositoryMock.findByEmail(USER_EMAIL)).thenReturn(Optional.of(existing));
        UserDto fromService = userService.getByEmail(USER_EMAIL);
        assertEquals(existingDto, fromService);
    }

    @Test
    void getByEmailNotExistingTest() {
        when(userRepositoryMock.findByEmail(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getByEmail(""));
    }

    @Test
    void deleteExistingTest() {
        when(userRepositoryMock.findById(ID_EXISTING)).thenReturn(Optional.of(existing));
        userService.delete(ID_EXISTING);
        verify(userRepositoryMock, times(1)).save(Mockito.any());
    }

    @Test
    void deleteNotExistingTest() {
        when(userRepositoryMock.findById(ID_NOT_EXISTING)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.delete(ID_NOT_EXISTING));
        verify(userRepositoryMock, times(0)).save(Mockito.any());
    }

    private User initUser(long id,
                          String email,
                          String password,
                          String firstName,
                          String lastName,
                          User.Role role) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        return user;
    }

    private UserDto initUserDto(long id,
                                String email,
                                String password,
                                String firstName,
                                String lastName,
                                RoleDto roleDto) {
        UserDto dto = new UserDto();
        dto.setId(id);
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setRole(roleDto);
        return dto;
    }
}