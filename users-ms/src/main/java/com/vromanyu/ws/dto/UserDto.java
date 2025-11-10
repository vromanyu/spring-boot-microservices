package com.vromanyu.ws.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDto(
        Integer id,

        @JsonProperty("firstname")
        String firstName,

        @JsonProperty("lastname")
        String lastName,

        String email) {
}
