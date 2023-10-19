package com.nester.Rew.service;

import com.nester.Rew.service.dto.apartment.ApartmentDto;
import com.nester.Rew.service.dto.apartment.ApartmentDtoForSave;
import com.nester.Rew.service.dto.apartment.ApartmentDtoForUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApartmentService {
    ApartmentDto create(ApartmentDtoForSave dto);

    Page<ApartmentDto> getAll(Pageable pageable);

    ApartmentDto getById(Long id);

    ApartmentDto update(ApartmentDtoForUpdate dto);

    void delete(Long id);

    List<ApartmentDto> getAllByUser(String email);

    Page<ApartmentDto> getAllActive(Pageable pageable);
}
