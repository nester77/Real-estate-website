package com.nester.Rew.service.impl;

import com.nester.Rew.data.entity.Apartment;
import com.nester.Rew.data.entity.User;
import com.nester.Rew.data.repository.ApartmentRepository;
import com.nester.Rew.data.repository.UserRepository;
import com.nester.Rew.service.ApartmentService;
import com.nester.Rew.service.dto.apartment.ApartmentDto;
import com.nester.Rew.service.dto.apartment.ApartmentDtoForSave;
import com.nester.Rew.service.dto.apartment.ApartmentDtoForUpdate;
import com.nester.Rew.service.exception.NotFoundException;
import com.nester.Rew.service.mapper.ApartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {
    private static final String APARTMENT_NOT_FOUND_MSG = "Apartment not found";
    private static final String USER_NOT_FOUND_MSG = "User not found";
    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;
    private final ApartmentMapper apartmentMapper;

    @Override
    public ApartmentDto create(ApartmentDtoForSave dto) {
        String email = dto.getOwner().getEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MSG));
        Apartment entity = apartmentMapper.apartmentDtoForSavingToApartment(dto);
        entity.setOwner(user);
        entity.setActive(true);
        Apartment created = apartmentRepository.save(entity);
        return apartmentMapper.apartmentToApartmentDto(created);
    }

    @Override
    public Page<ApartmentDto> getAll(Pageable pageable) {
        Page<Apartment> apartments = apartmentRepository.findAll(pageable);
        return apartments.map(apartmentMapper::apartmentToApartmentDto);
    }

    @Override
    public List<ApartmentDto> getAllByUser(String email) {
        List<Apartment> apartments = apartmentRepository.findAllByUser(email);
        return apartments.stream().map(apartmentMapper::apartmentToApartmentDto).collect(Collectors.toList());
    }

    @Override
    public ApartmentDto getById(Long id) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(APARTMENT_NOT_FOUND_MSG));
        return apartmentMapper.apartmentToApartmentDto(apartment);
    }

    @Override
    public ApartmentDto update(ApartmentDtoForUpdate dto) {
        Apartment oldApartment = apartmentRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException(APARTMENT_NOT_FOUND_MSG));
        Apartment newApartment = apartmentMapper.apartmentDtoForUpdatingToApartment(dto);
        Apartment updated = apartmentRepository.save(newApartment);
        return apartmentMapper.apartmentToApartmentDto(updated);
    }

    @Override
    public void delete(Long id) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(APARTMENT_NOT_FOUND_MSG));
        if (!apartment.isActive()) {
            throw new NotFoundException(APARTMENT_NOT_FOUND_MSG);
        }
        apartment.setActive(false);
        apartmentRepository.save(apartment);
    }
}
