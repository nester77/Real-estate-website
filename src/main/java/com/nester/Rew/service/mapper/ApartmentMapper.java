package com.nester.Rew.service.mapper;

import com.nester.Rew.data.entity.Apartment;
import com.nester.Rew.service.dto.apartment.ApartmentDto;
import com.nester.Rew.service.dto.apartment.ApartmentDtoForSave;
import com.nester.Rew.service.dto.apartment.ApartmentDtoForUpdate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {
    Apartment apartmentDtoForSavingToApartment(ApartmentDtoForSave apartmentDtoForSaving);

    ApartmentDto apartmentToApartmentDto(Apartment apartment);

    Apartment apartmentDtoToApartment(ApartmentDto apartmentDto);

    Apartment apartmentDtoForUpdatingToApartment(ApartmentDtoForUpdate apartmentDtoForUpdating);
}
