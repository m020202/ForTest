package com.example.demo.service.CategoryService;

import com.example.demo.domain.Category;
import com.example.demo.domain.Member;
import com.example.demo.dto.category.CategoryResponseDTO;

import java.util.List;

public interface CategoryQueryService {
    List<CategoryResponseDTO.QueryResultDto> findAll(Member member);
}
