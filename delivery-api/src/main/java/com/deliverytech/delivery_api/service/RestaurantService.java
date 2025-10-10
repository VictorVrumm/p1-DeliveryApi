package com.deliverytech.delivery_api.service;
 
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery_api.dto.RestaurantDTO;
import com.deliverytech.delivery_api.entity.Restaurant;
import com.deliverytech.delivery_api.repository.IRestaurantRepository;
 
@Service
public class RestaurantService implements IRestaurantService {
 
    @Autowired
    private IRestaurantRepository repository;
 
    public RestaurantService(IRestaurantRepository repository)
    {
        this.repository = repository;
    }
 
    public RestaurantService() {
        super();
    }
 
    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
       ModelMapper mapper = new ModelMapper();
 
       Restaurant entity = mapper.map(restaurantDTO, Restaurant.class);
       Restaurant restaurant = repository.save(entity);
       RestaurantDTO dto = mapper.map(restaurant, RestaurantDTO.class);
 
       return dto;
    }
 
    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        ModelMapper mapper = new ModelMapper();  
        List<Restaurant> restaurantes = repository.findAll();
 
        List<RestaurantDTO> restaurantDtoList = Arrays.asList(mapper.map(restaurantes, RestaurantDTO[].class));
 
        return restaurantDtoList;
        //return repository.findAll().stream().map(this::ConvertEntityToDto).collect(Collectors.toList());
    }
   
    private Restaurant ConvertDtoToEntity(RestaurantDTO dto) {
        Restaurant entity = new Restaurant();
        entity.setNome(dto.getName());
        entity.setDescricao((dto.getDescription()));
        return entity;
    }
 
    private RestaurantDTO ConvertEntityToDto(Restaurant entity) {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setName(entity.getNome());
        dto.setDescription((entity.getDescricao()));
        return dto;
    }
 
}