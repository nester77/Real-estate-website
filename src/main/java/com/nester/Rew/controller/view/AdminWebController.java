package com.nester.Rew.controller.view;

import com.nester.Rew.service.ApartmentService;
import com.nester.Rew.service.UserService;
import com.nester.Rew.service.dto.apartment.ApartmentDto;
import com.nester.Rew.service.dto.apartment.ApartmentDtoForUpdate;
import com.nester.Rew.service.dto.user.UserDto;
import com.nester.Rew.service.dto.user.UserDtoForUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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
    public static final int SIZE_PAGE = 5;
    public static final String SORT_PAGE = "id";
    private final UserService userService;
    private final ApartmentService apartmentService;

    @GetMapping("/users")
    public String getAllUsers(Model model, @PageableDefault(size = SIZE_PAGE) @SortDefault(SORT_PAGE) Pageable pageable) {
        Page<UserDto> users = userService.getAll(pageable);
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/apartments")
    public String getAllApartments(Model model, @PageableDefault(size = SIZE_PAGE) @SortDefault(SORT_PAGE) Pageable pageable) {
        Page<ApartmentDto> apartments = apartmentService.getAll(pageable);
        model.addAttribute("apartments", apartments);
        return "apartment/apartments";
    }

    @GetMapping("/user/{id}")
    public String getById(@PathVariable Long id, Model model) {
        UserDto user = userService.getById(id);
        model.addAttribute("user", user);
        List<ApartmentDto> apartments = apartmentService.getAllByUser(user.getEmail());
        model.addAttribute("apartments", apartments);
        return "user/user";
    }

    @GetMapping("/update/user/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        UserDto toUpdate = userService.getById(id);
        model.addAttribute("user", toUpdate);
        return "user/update_user";
    }

    @PostMapping("/update/user/{id}")
    public String update(@PathVariable(value = "id") Long id,
                         @ModelAttribute("user") UserDtoForUpdate dto) {
        dto.setId(id);
        userService.update(dto);
        return "redirect:/admins/user/" + id;
    }

    @GetMapping("/delete/user/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        userService.delete(id);
        return "redirect:/admins/users";
    }

    @GetMapping("/update/apartment/{id}")
    public String updateApartmentForm(@PathVariable Long id, Model model) {
        ApartmentDto toUpdate = apartmentService.getById(id);
        model.addAttribute("apartment", toUpdate);
        return "apartment/update_apartment";
    }

    @PostMapping("/update/apartment/{id}")
    public String updateApartment(@PathVariable(value = "id") Long id,
                                  @ModelAttribute("apartment") ApartmentDtoForUpdate dto) {
        dto.setId(id);
        String email = apartmentService.getById(id).getOwner().getEmail();
        UserDto owner = new UserDto();
        owner.setEmail(email);
        dto.setOwner(owner);
        apartmentService.update(dto);
        return "redirect:/apartments";
    }

    @GetMapping("/delete/apartment/{id}")
    public String deleteApartment(@PathVariable(value = "id") Long id) {
        apartmentService.delete(id);
        return "redirect:/apartments";
    }

}
