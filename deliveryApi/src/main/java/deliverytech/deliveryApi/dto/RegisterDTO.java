package deliverytech.deliveryApi.dto;

import deliverytech.deliveryApi.entity.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}