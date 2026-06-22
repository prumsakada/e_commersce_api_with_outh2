package co.istad.sakkda.ecommerceapi.feature.category;

import co.istad.sakkda.ecommerceapi.feature.category.dto.CategoryResponse;
import co.istad.sakkda.ecommerceapi.feature.category.dto.CreateCategoryRequest;
import co.istad.sakkda.ecommerceapi.feature.category.dto.PatchCategoryRequest;
import co.istad.sakkda.ecommerceapi.feature.category.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;

public interface CategoryService {
    void deleteById(Integer id);

    CategoryResponse patchById(Integer id, PatchCategoryRequest patchCategoryRequest);

    CategoryResponse getById(Integer id);

    CategoryResponse updateById(Integer id, UpdateCategoryRequest updateCategoryRequest);

    Page<CategoryResponse> getCategories(int pageNumber, int pageSize);

    CategoryResponse createNew(CreateCategoryRequest createCategoryRequest);
}
