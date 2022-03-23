package org.csystem.app.api.product.repository;

import org.csystem.app.api.product.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author onder sahin
 */
public interface IProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
