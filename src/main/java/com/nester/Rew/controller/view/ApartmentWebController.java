package com.nester.Rew.controller.view;

import com.nester.Rew.service.ApartmentService;
import com.nester.Rew.service.dto.apartment.ApartmentDto;
import com.nester.Rew.service.dto.apartment.ApartmentDtoForSave;
import com.nester.Rew.service.dto.apartment.ApartmentDtoForUpdate;
import com.nester.Rew.service.dto.user.UserDto;
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
@RequestMapping("/apartments")
@RequiredArgsConstructor
public class ApartmentWebController {
    public static final int SIZE_PAGE = 10;
    public static final String SORT_PAGE = "id";
    private final ApartmentService apartmentService;

    @GetMapping("/create")
    public String createForm() {
        return "apartment/create_apartment";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("apartment") ApartmentDtoForSave dto) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = userAppDetails.getUsername();
        UserDto owner = new UserDto();
        owner.setEmail(email);
        dto.setOwner(owner);
        ApartmentDto created = apartmentService.create(dto);
        return "redirect:/apartments/" + created.getId();
    }

    @GetMapping
    public String getAllActive(Model model, @PageableDefault(size = SIZE_PAGE) @SortDefault(SORT_PAGE) Pageable pageable) {
        Page<ApartmentDto> apartments = apartmentService.getAllActive(pageable);
        model.addAttribute("apartments", apartments);
        return "apartment/apartments";
    }

    @GetMapping("/personal")
    public String getAllByUser(Model model) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = userAppDetails.getUsername();
        List<ApartmentDto> apartments = apartmentService.getAllByUser(email);
        model.addAttribute("apartments", apartments);
        return "apartment/personal_apartments";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        ApartmentDto apartment = apartmentService.getById(id);
        model.addAttribute("apartment", apartment);
        return "apartment/apartment";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        ApartmentDto toUpdate = apartmentService.getById(id);
        model.addAttribute("apartment", toUpdate);
        return "apartment/update_apartment";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") Long id,
                         @ModelAttribute("apartment") ApartmentDtoForUpdate dto) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = userAppDetails.getUsername();
        if (apartmentService.getById(id).getOwner().getEmail().equals(email)) {
            dto.setId(id);
            UserDto owner = new UserDto();
            owner.setEmail(email);
            dto.setOwner(owner);
            apartmentService.update(dto);
        }
        return "redirect:/apartments/" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = userAppDetails.getUsername();
        if (apartmentService.getById(id).getOwner().getEmail().equals(email)) {
            apartmentService.delete(id);
        }
        return "redirect:/apartments";
    }
}
