package co.istad.sakkda.ecommerceapi.feature.category;

import co.istad.sakkda.ecommerceapi.feature.category.dto.CategoryResponse;
import co.istad.sakkda.ecommerceapi.feature.category.dto.CreateCategoryRequest;
import co.istad.sakkda.ecommerceapi.feature.category.dto.PatchCategoryRequest;
import co.istad.sakkda.ecommerceapi.feature.category.dto.UpdateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void deleteById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category ID not found"
                ));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryResponse patchById(Integer id, PatchCategoryRequest patchCategoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category ID not found"
                ));

        if (patchCategoryRequest.name() != null) {
            if (categoryRepository.existsByNameAndIdNot(patchCategoryRequest.name(), id)) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Category name already exists"
                );
            }
        }

        categoryMapper.patchCategoryRequestToCategory(patchCategoryRequest, category);
        category = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryResponse(category);
    }

    @Override
    public CategoryResponse getById(Integer id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::categoryToCategoryResponse)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category ID not found"
                ));
    }

    @Override
    public CategoryResponse updateById(Integer id, UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category ID not found"
                ));

        if (categoryRepository.existsByNameAndIdNot(updateCategoryRequest.name(), id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category name already exists"
            );
        }

        categoryMapper.updateCategoryRequestToCategory(updateCategoryRequest, category);
        category = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryResponse(category);
    }

    @Override
    public Page<CategoryResponse> getCategories(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::categoryToCategoryResponse);
    }

    @Override
    public CategoryResponse createNew(CreateCategoryRequest createCategoryRequest) {
        if (categoryRepository.existsByName(createCategoryRequest.name())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category name already exists"
            );
        }

        Category category = new Category();
        category.setName(createCategoryRequest.name());
        category.setDescription(createCategoryRequest.description());
        category.setIcon(createCategoryRequest.icon());

        category = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryResponse(category);
    }
}
