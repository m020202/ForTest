package com.example.demo.dto.category;

import lombok.Getter;
import lombok.Setter;

public class CategoryRequestDTO {
    @Setter
    @Getter
    public static class CreateOrUpdateDto {
        String type;
    }
}
