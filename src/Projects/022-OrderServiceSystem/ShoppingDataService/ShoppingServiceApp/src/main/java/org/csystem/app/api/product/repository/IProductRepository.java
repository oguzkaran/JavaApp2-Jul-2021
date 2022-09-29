package org.csystem.app.api.product.repository;

import org.csystem.app.api.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author onder sahin
 */
public interface IProductRepository extends JpaRepository<Product,Integer> {

}
