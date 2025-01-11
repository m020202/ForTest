package com.example.demo.service.CategoryService;

import com.example.demo.domain.Member;
import com.example.demo.dto.category.CategoryRequestDTO;
import com.example.demo.dto.category.CategoryResponseDTO;

public interface CategoryCommandService {
    CategoryResponseDTO.CommandResultDto create(Member member, CategoryRequestDTO.CreateOrUpdateDto request);

    CategoryResponseDTO.CommandResultDto delete(Long id);

    CategoryResponseDTO.CommandResultDto update(Long id, CategoryRequestDTO.CreateOrUpdateDto request);

}
