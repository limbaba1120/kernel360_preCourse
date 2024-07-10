package com.example.restapi.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ContactRequest {
    private String name;
    private String phoneNumber;
    // private Integer userAge; // JSON으로 할때 reference type 사용
    private String email;
}
