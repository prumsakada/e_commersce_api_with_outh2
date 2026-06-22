package co.istad.sakkda.ecommerceapi.feature.product;

import co.istad.sakkda.ecommerceapi.feature.product.dto.CreateProductRequest;
import co.istad.sakkda.ecommerceapi.feature.product.dto.PatchProductRequest;
import co.istad.sakkda.ecommerceapi.feature.product.dto.ProductResponse;
import co.istad.sakkda.ecommerceapi.feature.product.dto.UpdateProductRequest;
import org.springframework.data.domain.Page;

public interface ProductService {
    void deleteByCode(String code);

    ProductResponse patchByCode(String code, PatchProductRequest patchProductRequest);

    ProductResponse getProductByCode(String code);

    ProductResponse updateByCode(String code, UpdateProductRequest updateProductRequest);

    Page<ProductResponse> getProducts(int pageNumber, int pageSize);

    ProductResponse createNew(CreateProductRequest createProductRequest);

}
