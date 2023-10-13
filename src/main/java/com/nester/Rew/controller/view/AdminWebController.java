package com.nester.Rew.controller.view;

import com.nester.Rew.service.ApartmentService;
import com.nester.Rew.service.UserService;
import com.nester.Rew.service.dto.apartment.ApartmentDto;
import com.nester.Rew.service.dto.user.UserDto;
import com.nester.Rew.service.dto.user.UserDtoForUpdate;
import com.nester.Rew.service.impl.UserAppDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminWebController {
    public static final int SIZE_PAGE = 10;
    public static final String SORT_PAGE = "id";
    private final UserService userService;
    private final ApartmentService apartmentService;

    @GetMapping
    public String getAll(Model model, @PageableDefault(size = SIZE_PAGE) @SortDefault(SORT_PAGE) Pageable pageable) {
        Page<UserDto> users = userService.getAll(pageable);
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        UserDto user = userService.getById(id);
        model.addAttribute("user", user);
        List<ApartmentDto> apartments = apartmentService.getAllByUser(user.getEmail());
        model.addAttribute("apartments", apartments);
        return "user";
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
        return "personal_page";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        UserDto toUpdate = userService.getById(id);
        model.addAttribute("user", toUpdate);
        return "update_user";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") Long id,
                         @ModelAttribute("user") UserDtoForUpdate dto) {
        dto.setId(id);
        userService.update(dto);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
