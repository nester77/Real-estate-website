package com.nester.Rew.controller.rest;

import com.nester.Rew.service.ApartmentService;
import com.nester.Rew.service.dto.apartment.ApartmentDto;
import com.nester.Rew.service.dto.apartment.ApartmentDtoForSave;
import com.nester.Rew.service.dto.apartment.ApartmentDtoForUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/apartments")
@RequiredArgsConstructor
public class ApartmentRestController {
    private final ApartmentService apartmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApartmentDto create(@RequestBody ApartmentDtoForSave dto) {
        return apartmentService.create(dto);
    }

    @GetMapping
    public Page<ApartmentDto> getAll(Pageable pageable) {
        return apartmentService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ApartmentDto getById(@PathVariable Long id) {
        return apartmentService.getById(id);
    }

    @PutMapping("/{id}")
    public ApartmentDto update(@PathVariable Long id, @RequestBody ApartmentDtoForUpdate dto) {
        dto.setId(id);
        return apartmentService.update(dto);
    }

    @PatchMapping("/{id}")
    public ApartmentDto updatePartly(@PathVariable Long id, @RequestBody ApartmentDtoForUpdate dto) {
        dto.setId(id);
        return apartmentService.update(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        apartmentService.delete(id);
    }
}
