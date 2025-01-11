package com.example.demo.converter;

import com.example.demo.domain.Category;
import com.example.demo.domain.Member;
import com.example.demo.dto.category.CategoryRequestDTO;
import com.example.demo.dto.category.CategoryResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryConverter {
    public static Category toCategory(Member member, CategoryRequestDTO.CreateOrUpdateDto request) {
        return Category.builder().member(member).type(request.getType()).build();
    }

    public static CategoryResponseDTO.CommandResultDto toCommandResultDto(Long id) {
        return CategoryResponseDTO.CommandResultDto.builder().categoryId(id).build();
    }

    public static List<CategoryResponseDTO.QueryResultDto> toQueryResultDto(List<Category> categories) {
        List<CategoryResponseDTO.QueryResultDto> result = categories.stream().map(c -> CategoryResponseDTO.QueryResultDto.builder().categoryId(c.getId()).type(c.getType()).build()).collect(Collectors.toList());
        return result;
    }
}
