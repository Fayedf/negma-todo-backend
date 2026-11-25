package com.negma.Controller;

import com.negma.DTO.CategoryRequest;
import com.negma.DTO.CategoryResponse;
import com.negma.Entity.Category;
import com.negma.Repository.CategoryRepo;
import com.negma.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/categories")

@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})


public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<CategoryResponse>> getCategories() {

        return ResponseEntity.ok().body(categoryService.getAllCategories());

    }


    @PostMapping("")
    public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse =categoryService.createCategory(categoryRequest);

        return ResponseEntity.ok().body(categoryResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable UUID id,@Valid @RequestBody CategoryRequest categoryRequest) {

        CategoryResponse categoryResponse =categoryService.updateCategory(id,categoryRequest);

        return ResponseEntity.ok().body(categoryResponse);


    }
}
