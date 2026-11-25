package com.negma.Service;

import com.negma.DTO.CategoryRequest;
import com.negma.DTO.CategoryResponse;
import com.negma.Entity.Category;
import com.negma.Entity.User;
import com.negma.Mapper.CategoryMapper;
import com.negma.Repository.CategoryRepo;
import com.negma.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo  categoryRepo;
    private final CategoryMapper categoryMapper;
    private final CurrentUserService currentUserService;
    private final UserRepo userRepo;

    // add category to task and user
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

      User currentUser =   currentUserService.getCurrentUser();
      Category category = categoryMapper.toEntity(categoryRequest);

      category.setUser(currentUser);
        category =  categoryRepo.save(category);

      return categoryMapper.toResponse(category);
    }

    // edit category
    public CategoryResponse updateCategory(
            UUID id,
            CategoryRequest categoryRequest) {

        User currentUser = currentUserService.getCurrentUser();

        Category category = categoryRepo
                .findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found"
                ));

        category.setCategoryName(categoryRequest.getCategoryName());

        category = categoryRepo.save(category);

        return categoryMapper.toResponse(category);
    }

    public List<CategoryResponse> getAllCategories() {

        User currentUser = currentUserService.getCurrentUser();

        List<Category> categories = categoryRepo.findAllByUser(currentUser);

        return categories.stream().map(categoryMapper::toResponse).toList();

    }



}
