package com.example.validation.model;

import com.example.validation.annotation.PhoneNumber;
import com.example.validation.annotation.YearMonth;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegisterRequest {

//    @NotBlank // != null && name != "" && name != " "
    private String name;

    private String nickName;

    @Size(min = 1, max = 12)
    @NotBlank
    private String password;

    @NotNull // 문자가 아니여서 NotBlank 불가
    @Min(1)
    @Max(100)
    private Integer age;

    @Email
    private String email;

    @PhoneNumber
    private String phoneNumber;

    @YearMonth
    private String registerAt;

    @AssertTrue(message = "name or nickName은 존재해야 합니다.")
    public boolean isNameCheck() {

        if (Objects.nonNull(name) && !name.isBlank()) {
            return true;
        }

        if (Objects.nonNull(nickName) && !nickName.isBlank()) {
            return true;
        }

        return false;
    }
}
