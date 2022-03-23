package org.csystem.app.api.product.controller.mapper;

import org.csystem.app.api.product.controller.dto.ProductSaveRequestDTO;
import org.csystem.app.api.product.domain.Product;
import org.csystem.app.api.product.domain.ProductCategory;
import org.csystem.app.api.product.service.ProductService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

/**
 * @author onder sahin
 */
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
public abstract class ProductMapper {

    @Autowired
    private ProductService productService;

    @Mapping(source = "dto.categoryId", target = "category",qualifiedByName = "idToProductCategory")
    public abstract Product toEntity(ProductSaveRequestDTO dto);

    @Mapping(source = "dto.categoryId", target = "category",qualifiedByName = "idToProductCategory")
    public abstract Product updateEntity(ProductSaveRequestDTO dto, @MappingTarget Product entity);


    @Named("idToProductCategory")
    ProductCategory idToProductCategory(Integer id)
    {
        return id == null ? null : productService.findProductCategoryById(id).orElseThrow(EntityNotFoundException::new);
    }

}
