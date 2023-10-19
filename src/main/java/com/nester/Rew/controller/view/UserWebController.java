package com.nester.Rew.controller.view;

import com.nester.Rew.service.ApartmentService;
import com.nester.Rew.service.UserService;
import com.nester.Rew.service.dto.apartment.ApartmentDto;
import com.nester.Rew.service.dto.user.UserDto;
import com.nester.Rew.service.dto.user.UserDtoForSave;
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
    private final UserService userService;
    private final ApartmentService apartmentService;

    @GetMapping("/create")
    public String createForm() {
        return "registration";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") UserDtoForSave dto) {
        UserDto created = userService.create(dto);
        return "login";
    }

    @GetMapping("/personal")
    public String getPersonalPage(Model model) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = userAppDetails.getUsername();
        UserDto user = userService.getByEmail(email);
        model.addAttribute("user", user);
        List<ApartmentDto> apartments = apartmentService.getAllByUser(email);
        model.addAttribute("apartments", apartments);
        return "user/personal_page";
    }

    @GetMapping("/update")
    public String updateForm(Model model) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = userAppDetails.getUsername();
        UserDto toUpdate = userService.getByEmail(email);
        model.addAttribute("user", toUpdate);
        return "user/update_user";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") UserDtoForUpdate dto) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = userAppDetails.getUsername();
        UserDto toUpdate = userService.getByEmail(email);
        dto.setId(toUpdate.getId());
        userService.update(dto);
        return "home";
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
        return "home";
    }
}
