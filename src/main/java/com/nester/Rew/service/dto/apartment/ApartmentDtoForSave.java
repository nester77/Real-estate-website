package com.nester.Rew.service.dto.apartment;

import com.nester.Rew.data.entity.User;
import lombok.Data;

@Data
public class ApartmentDtoForSave {
    private float square;
    private int numberOfRooms;
    private int floor;
    private String description;
    private int price;
    private String address;
    private User owner;
}
