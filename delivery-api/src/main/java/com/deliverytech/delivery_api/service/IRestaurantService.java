package com.deliverytech.delivery_api.service;
 
import java.util.List;
 
import org.springframework.stereotype.Service;
 
import com.deliverytech.delivery_api.dto.RestaurantDTO;
 
@Service
public interface IRestaurantService {
    List<RestaurantDTO> getAllRestaurants();
    RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);
}