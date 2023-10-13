package com.nester.Rew.controller.view;

import com.nester.Rew.service.ApartmentService;
import com.nester.Rew.service.dto.apartment.ApartmentDto;
import com.nester.Rew.service.dto.apartment.ApartmentDtoForSave;
import com.nester.Rew.service.dto.apartment.ApartmentDtoForUpdate;
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

@Controller
@RequestMapping("/apartments")
@RequiredArgsConstructor
public class ApartmentWebController {
    public static final int SIZE_PAGE = 10;
    public static final String SORT_PAGE = "id";
    private final ApartmentService apartmentService;

    @GetMapping("/create")
    public String createForm() {
        return "create_apartment";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("apartment") ApartmentDtoForSave dto) {
        ApartmentDto created = apartmentService.create(dto);
        return "redirect:/apartments/" + created.getId();
    }

    @GetMapping
    public String getAll(Model model, @PageableDefault(size = SIZE_PAGE) @SortDefault(SORT_PAGE) Pageable pageable) {
        Page<ApartmentDto> apartments = apartmentService.getAll(pageable);
        model.addAttribute("apartments", apartments);
        return "apartments";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        ApartmentDto apartment = apartmentService.getById(id);
        model.addAttribute("apartment", apartment);
        return "apartment";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        ApartmentDto toUpdate = apartmentService.getById(id);
        model.addAttribute("apartment", toUpdate);
        return "update_apartment";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") Long id,
                         @ModelAttribute("apartment") ApartmentDtoForUpdate dto) {
        dto.setId(id);
        apartmentService.update(dto);
        return "redirect:/apartments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        apartmentService.delete(id);
        return "redirect:/apartments";
    }
}
