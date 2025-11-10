package com.vromanyu.ws.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vromanyu.ws.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserDto(
        Integer id,

        @JsonProperty("firstname")
        @NotBlank
        @Length(min = 4, max = 255)
        String firstName,

        @JsonProperty("lastname")
        @NotBlank
        @Length(min = 4, max = 255)
        String lastName,

        @NotBlank
        @Email
        String email) {

    public static final class UserMapper {

        public static User toUser(UserDto userDto) {
            User user = new User();
            user.setId(userDto.id());
            user.setFirstname(userDto.firstName());
            user.setLastname(userDto.lastName());
            user.setEmail(userDto.email());
            return user;
        }

        public static UserDto toUserDto(User user) {
            return new UserDto(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
        }
    }
}
