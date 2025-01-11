package com.example.demo.controller;

import com.example.demo.converter.CategoryConverter;
import com.example.demo.domain.Member;
import com.example.demo.dto.category.CategoryRequestDTO;
import com.example.demo.dto.category.CategoryResponseDTO;
import com.example.demo.service.CategoryService.CategoryCommandService;
import com.example.demo.service.CategoryService.CategoryCommandServiceImpl;
import com.example.demo.service.CategoryService.CategoryQueryService;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryQueryService categoryQueryService;
    private final CategoryCommandService categoryCommandService;
    private final MemberService memberService;

    @PostMapping
    public CategoryResponseDTO.CommandResultDto create(HttpServletRequest httpServletRequest, @RequestBody CategoryRequestDTO.CreateOrUpdateDto request) {
        Member member = memberService.getMember(httpServletRequest);
        return categoryCommandService.create(member, request);
    }

    @DeleteMapping("/{id}")
    public CategoryResponseDTO.CommandResultDto delete(@PathVariable("id") Long id) {
        return categoryCommandService.delete(id);
    }

    @GetMapping
    public List<CategoryResponseDTO.QueryResultDto> read(HttpServletRequest httpServletRequest) {
        Member member = memberService.getMember(httpServletRequest);
        return categoryQueryService.findAll(member);
    }

    @PatchMapping("/{id}")
    public CategoryResponseDTO.CommandResultDto update(@PathVariable("id") Long id, @RequestBody CategoryRequestDTO.CreateOrUpdateDto request) {
        return categoryCommandService.update(id, request);
    }
}
