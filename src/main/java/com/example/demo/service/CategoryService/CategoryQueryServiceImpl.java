package com.example.demo.service.CategoryService;

import com.example.demo.converter.CategoryConverter;
import com.example.demo.domain.Category;
import com.example.demo.domain.Member;
import com.example.demo.dto.category.CategoryResponseDTO;
import com.example.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDTO.QueryResultDto> findAll(Member member) {
        List<Category> categories = categoryRepository.findAllByMemberId(member.getId());
        return CategoryConverter.toQueryResultDto(categories);
    }
}
