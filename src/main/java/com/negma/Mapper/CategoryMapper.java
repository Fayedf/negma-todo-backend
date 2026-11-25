package com.negma.Mapper;

import com.negma.DTO.CategoryRequest;
import com.negma.DTO.CategoryResponse;
import com.negma.Entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category category);



}
