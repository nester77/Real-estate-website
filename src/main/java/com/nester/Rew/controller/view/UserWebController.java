package com.nester.Rew.controller.view;

import com.nester.Rew.service.ApartmentService;
import com.nester.Rew.service.UserService;
import com.nester.Rew.service.dto.apartment.ApartmentDto;
import com.nester.Rew.service.dto.user.UserDto;
import com.nester.Rew.service.dto.user.UserDtoForUpdate;
import com.nester.Rew.service.impl.UserAppDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserWebController {
    public static final int SIZE_PAGE = 10;
    public static final String SORT_PAGE = "id";
    private final UserService userService;
    private final ApartmentService apartmentService;

    @GetMapping("/personal")
    public String getPersonalPage(Model model) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = userAppDetails.getUsername();
        UserDto user = userService.getByEmail(email);
        model.addAttribute("user", user);
        List<ApartmentDto> apartments = apartmentService.getAllByUser(email);
        model.addAttribute("apartments", apartments);
        return "personal_page";
    }

    @GetMapping("/update")
    public String updateForm(Model model) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = userAppDetails.getUsername();
        UserDto toUpdate = userService.getByEmail(email);
        model.addAttribute("user", toUpdate);
        return "update_user";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") UserDtoForUpdate dto) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = userAppDetails.getUsername();
        UserDto toUpdate = userService.getByEmail(email);
        dto.setId(toUpdate.getId());
        userService.update(dto);
        return "personal_page";
    }

    @GetMapping("/delete")
    public String delete() {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = userAppDetails.getUsername();
        List<ApartmentDto> apartments = apartmentService.getAllByUser(email);
        if (!apartments.isEmpty()) return "info";
        UserDto toDelete = userService.getByEmail(email);
        userService.delete(toDelete.getId());
        return "apartments";
    }
}
