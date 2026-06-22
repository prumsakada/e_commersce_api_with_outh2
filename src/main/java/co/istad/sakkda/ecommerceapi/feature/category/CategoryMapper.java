package co.istad.sakkda.ecommerceapi.feature.category;

import co.istad.sakkda.ecommerceapi.feature.category.dto.CategoryResponse;
import co.istad.sakkda.ecommerceapi.feature.category.dto.PatchCategoryRequest;
import co.istad.sakkda.ecommerceapi.feature.category.dto.UpdateCategoryRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse categoryToCategoryResponse(Category category);

    void updateCategoryRequestToCategory(UpdateCategoryRequest updateCategoryRequest,
                                         @MappingTarget Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchCategoryRequestToCategory(PatchCategoryRequest patchCategoryRequest,
                                        @MappingTarget Category category);
}
