package co.istad.sakkda.ecommerceapi.feature.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.istad.sakkda.ecommerceapi.feature.category.dto.CategoryResponse;
import co.istad.sakkda.ecommerceapi.feature.category.dto.CreateCategoryRequest;
import co.istad.sakkda.ecommerceapi.feature.category.dto.PatchCategoryRequest;
import co.istad.sakkda.ecommerceapi.feature.category.dto.UpdateCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Integer id) {
        log.info("getById: {}", id);
        return categoryService.getById(id);
    }

    @GetMapping
    public Page<CategoryResponse> getCategories(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        log.info("getCategories - page: {}, size: {}", pageNumber, pageSize);
        return categoryService.getCategories(pageNumber, pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createNew(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest
    ) {
        log.info("createNew: {}", createCategoryRequest);
        return categoryService.createNew(createCategoryRequest);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateById(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest
    ) {
        log.info("updateById - id: {}, request: {}", id, updateCategoryRequest);
        return categoryService.updateById(id, updateCategoryRequest);
    }

    @PatchMapping("/{id}")
    public CategoryResponse patchById(
            @PathVariable Integer id,
            @Valid @RequestBody PatchCategoryRequest patchCategoryRequest
    ) {
        log.info("patchById - id: {}, request: {}", id, patchCategoryRequest);
        return categoryService.patchById(id, patchCategoryRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        log.info("deleteById: {}", id);
        categoryService.deleteById(id);
    }
}
