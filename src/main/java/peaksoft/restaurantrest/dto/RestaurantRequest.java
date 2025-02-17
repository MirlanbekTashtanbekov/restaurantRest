package peaksoft.restaurantrest.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantRequest {
    String name;
    String location;
    String restType;
    int numberOfEmployees;
    String service;
}
