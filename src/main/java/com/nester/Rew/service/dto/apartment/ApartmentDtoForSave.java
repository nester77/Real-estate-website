package com.nester.Rew.service.dto.apartment;

import com.nester.Rew.service.dto.user.UserDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ApartmentDtoForSave {
    @Min(value = 0, message = "Square should be greater than 0")
    private float square;

    @Min(value = 0, message = "Number of rooms should be greater than 0")
    private int numberOfRooms;

    @Min(value = 0, message = "Floor should be greater than 0")
    private int floor;

    private String description;

    @Min(value = 0, message = "Price should be greater than 0")
    private int price;

    @NotEmpty(message = "Address should be empty")
    private String address;

    private UserDto owner;
}
