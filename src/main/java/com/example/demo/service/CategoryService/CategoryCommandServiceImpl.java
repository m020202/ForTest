package com.example.demo.service.CategoryService;

import com.example.demo.converter.CategoryConverter;
import com.example.demo.domain.Category;
import com.example.demo.domain.Member;
import com.example.demo.dto.category.CategoryRequestDTO;
import com.example.demo.dto.category.CategoryResponseDTO;
import com.example.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryCommandServiceImpl implements CategoryCommandService {
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategoryResponseDTO.CommandResultDto create(Member member, CategoryRequestDTO.CreateOrUpdateDto request) {
        Category category = CategoryConverter.toCategory(member, request);
        Long id = categoryRepository.save(category).getId();
        return CategoryConverter.toCommandResultDto(id);
    }

    @Override
    @Transactional
    public CategoryResponseDTO.CommandResultDto delete(Long id) {
        categoryRepository.deleteById(id);
        return CategoryConverter.toCommandResultDto(id);
    }

//    @Override
//    @Transactional
//    public CategoryResponseDTO.CommandResultDto update(Long id, CategoryRequestDTO.CreateOrUpdateDto request) {
//        Long updatedId = categoryRepository.updateTypeById(id, request.getType());
//        return CategoryConverter.toCommandResultDto(updatedId);
//    }

    @Override
    @Transactional
    public CategoryResponseDTO.CommandResultDto update(Long id, CategoryRequestDTO.CreateOrUpdateDto request) {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.changeType(request.getType());
        return CategoryConverter.toCommandResultDto(category.getId());
    }
}
