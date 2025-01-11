package com.example.demo.repository;

import com.example.demo.domain.Category;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByMemberId(Long id);

    @Modifying
    @Query("UPDATE Category c SET c.type = :type WHERE c.id = :id")
    Long updateTypeById(@Param("id") Long id, @Param("type") String type);
}
